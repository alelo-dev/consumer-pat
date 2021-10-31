package br.com.alelo.consumer.consumerpat.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.repository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.repository.ExtractRepository;

@Service
public class ConsumerService {
	
	@Autowired
	private ConsumerRepository repository;
	
	@Autowired
	private ExtractRepository extractRepository;
	
	public List<Consumer> listAllConsumers(Pageable pageable) {
		Page<Consumer> recordsPage = repository.findAll(pageable);
	    return recordsPage.getContent();
	}
	
	public void createConsumer(Consumer consumer) {
		repository.save(consumer);
	}
	
	// Não deve ser possível alterar o saldo do cartão
	public void updateConsumer(Consumer consumer) {
		repository.save(consumer);
	}
	
    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
	public void setBalance(int cardNumber, double value) throws Exception {
		Consumer consumer = null;
        consumer = repository.findByDrugstoreNumber(cardNumber);
        
        if(consumer != null) {
            // é cartão de farmácia
            consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() + value);
        } else {
            consumer = repository.findByFoodCardNumber(cardNumber);
            if(consumer != null) {
                // é cartão de refeição
                consumer.setFoodCardBalance(consumer.getFoodCardBalance() + value);
            } else {
                consumer = repository.findByFuelCardNumber(cardNumber);
                if(consumer!=null) {
                	// É cartão de combustivel
                	consumer.setFuelCardBalance(consumer.getFuelCardBalance() + value);
                }
                else {
                	throw new Exception("ERROR: Card not found.");
                }
            }
        }
        repository.save(consumer);
	}
	
	public void buy(int establishmentType, String establishmentName, int cardNumber, String productDescription, double value) throws Exception {
		 Consumer consumer = null;
	        /* O valores só podem ser debitados dos cartões com os tipos correspondentes ao tipo do estabelecimento da compra.
	        *  Exemplo: Se a compra é em um estabelecimeto de Alimentação(food) então o valor só pode ser debitado do cartão e alimentação
	        *
	        * Tipos de estabelcimentos
	        * 1 - Alimentação (food)
	        * 2 - Farmácia (DrugStore)
	        * 3 - Posto de combustivel (Fuel)
	        */

	        if (establishmentType == 1) {
	            // Para compras no cartão de alimentação o cliente recebe um desconto de 10%
	            consumer = repository.findByFoodCardNumber(cardNumber);
	            if(consumer!=null) {
		            value = addFoodCashback(value);
		            checkFoodFunds(value, consumer);
	            	consumer.setFoodCardBalance(consumer.getFoodCardBalance() - value);	
	            }
	        }else if(establishmentType == 2) {
	            consumer = repository.findByDrugstoreNumber(cardNumber);
	            if(consumer!=null) {
	            	checkDrugstoreFunds(value, consumer);
	            	consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() - value);	
	            }
	        } else {
	        	if(establishmentType == 3) {
		            // Nas compras com o cartão de combustivel existe um acrescimo de 35%;
		            consumer = repository.findByFuelCardNumber(cardNumber);
		            if(consumer!=null) {
			            value = addFuelTax(value);
			            checkFuelFunds(value, consumer);
		            	consumer.setFuelCardBalance(consumer.getFuelCardBalance() - value);	
		            }
	        	}
	        	else{
	        		throw new Exception("ERROR: Invalid establishment.");
	            }
	        }
	        
	        if(consumer!=null) {
	        	repository.save(consumer);	
	        }
	        else {
	        	throw new Exception("ERROR: Consumer not found.");	
	        }

	        Extract extract = new Extract(establishmentName, productDescription, new Date(), cardNumber, value);
	        extractRepository.save(extract);
	}

	private double addFuelTax(double value) {
		Double tax  = (value / 100) * 35;
		value = value + tax;
		return value;
	}

	private double addFoodCashback(double value) {
		Double cashback  = (value / 100) * 10;
		value = value - cashback;
		return value;
	}

	private void checkFuelFunds(double value, Consumer consumer) throws Exception {
		if(value > consumer.getFuelCardBalance()) {
			throw new Exception("ERROR: Insufficient funds.");
		}
	}
	
	private void checkFoodFunds(double value, Consumer consumer) throws Exception {
		if(value > consumer.getFoodCardBalance()) {
			throw new Exception("ERROR: Insufficient funds.");
		}
	}
	
	private void checkDrugstoreFunds(double value, Consumer consumer) throws Exception {
		if(value > consumer.getDrugstoreCardBalance()) {
			throw new Exception("ERROR: Insufficient funds.");
		}
	}
	
}
