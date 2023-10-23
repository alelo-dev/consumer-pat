package br.com.alelo.consumer.consumerpat.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.alelo.consumer.consumerpat.dto.BuyDTO;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;

/**
 * Camada para isolar o dominio através dos serviços e tratar a possiveis regras
 * @author julio.silva
 * @date 23/10/2023
 */
@Service
public class ConsumerService extends BaseService {

	private static final int MULTIPLIER_BY_35 = 35;

	private static final int MULTIPLIER_BY_10 = 10;

	private static final int DIVIDER_BY_100 = 100;

	private static final int TYPE_ESTABLISHMENT_DRUGSTORE = 2;

	private static final int TYPE_ESTABLISHMENT_FOOD = 1;

	@Autowired
    ConsumerRepository repository;

    @Autowired
    ExtractRepository extractRepository;
    
    /**
     *  Listar todos os clientes (obs.: tabela possui cerca de 50.000 registros) 
     */
    public List<Consumer> listAllConsumers() {
        return repository.getAllConsumersList();
    }
    
    /**
     * Cadastrar novos clientes
     * @param consumer
     */
    public void createConsumer(@RequestBody Consumer consumer) {
        repository.save(consumer);
    }
    
    /**
     * Atualizar cliente, lembrando que não deve ser possível alterar o saldo do cartão
     * @param consumer
     */
    public void updateConsumer(@RequestBody Consumer consumer) {
    	
    	// TODO: Implementar Regra
    	
        repository.save(consumer);
    }
    
    /**
     * Credito de valor no cartão
     * cardNumber: número do cartão
     * value: valor a ser creditado (adicionado ao saldo)
     */
    public void setBalance(int cardNumber, double value) {
         Consumer consumer = repository.findByDrugstoreNumber(cardNumber);
        if(consumer != null) { 
        	// é cartão de farmácia
            setBalanceDrugstore(value, consumer);
        } else {
            consumer = repository.findByFoodCardNumber(cardNumber);
            if(consumer != null) { 
            	// é cartão de refeição
                setBalanceFood(value, consumer);
            } else {
            	// É cartão de combustivel
                consumer = repository.findByFuelCardNumber(cardNumber);
                setBalanceFuel(value, consumer);
            }
        }
    }

    /**
     * Credito de valor no cartão de combustivel
     * @param value
     * @param consumer
     */
	private void setBalanceFuel(double value, Consumer consumer) {
		consumer.fuelCardBalance = consumer.fuelCardBalance + value;
		repository.save(consumer);
	}

	 /**
     * Credito de valor no cartão de alimentacao
     * @param value
     * @param consumer
     */
	private void setBalanceFood(double value, Consumer consumer) {
		consumer.foodCardBalance = consumer.foodCardBalance + value;
		repository.save(consumer);
	}

	 /**
     * Credito de valor no cartão de farmacia
     * @param value
     * @param consumer
     */
	private void setBalanceDrugstore(double value, Consumer consumer) {
		consumer.drugstoreCardBalance = consumer.drugstoreCardBalance + value;
		repository.save(consumer);
	}
	
    /**
     * Débito de valor no cartão (compra)
     *
     * establishmentType: tipo do estabelecimento comercial
     * establishmentName: nome do estabelecimento comercial
     * cardNumber: número do cartão
     * productDescription: descrição do produto
     * value: valor a ser debitado (subtraído)
     */
    public void buy(BuyDTO buyDto) {
        Consumer consumer = null;
        /* O valor só podem ser debitado do catão com o tipo correspondente ao tipo do estabelecimento da compra.

        *  Exemplo: Se a compra é em um estabelecimeto de Alimentação (food) então o valor só pode ser debitado do cartão alimentação
        *
        * Tipos dos estabelcimentos:
        *    1) Alimentação (Food)
        *    2) Farmácia (DrugStore)
        *    3) Posto de combustivel (Fuel)
        */

        if (buyDto.establishmentType == TYPE_ESTABLISHMENT_FOOD) {
        	// Para compras no cartão de alimentação o cliente recebe um desconto de 10%
            buyFood(buyDto);           
        }else if(buyDto.establishmentType == TYPE_ESTABLISHMENT_DRUGSTORE) {
            buyDrugstore(buyDto);

        } else {
            // Nas compras com o cartão de combustivel existe um acrescimo de 35%;
            buyFuel(buyDto);
        }

        Extract extract = new Extract(buyDto.establishmentName, buyDto.productDescription, new Date(), buyDto.cardNumber, buyDto.value);
        extractRepository.save(extract);
    }

    /**
     * Nas compras com o cartão de combustivel existe um acrescimo de 35%;
     * @param buyDto
     */
	private void buyFuel(BuyDTO buyDto) {
		Consumer consumer;
		Double tax  = (buyDto.value / DIVIDER_BY_100) * MULTIPLIER_BY_35;
		buyDto.value = buyDto.value + tax;

		consumer = repository.findByFuelCardNumber(buyDto.cardNumber);
		consumer.fuelCardBalance = consumer.fuelCardBalance - buyDto.value;
		repository.save(consumer);
	}

	/**
	 * * @param buyDto
	 */
	private void buyDrugstore(BuyDTO buyDto) {
		Consumer consumer;
		consumer = repository.findByDrugstoreNumber(buyDto.cardNumber);
		consumer.drugstoreCardBalance = consumer.drugstoreCardBalance - buyDto.value;
		repository.save(consumer);
	}

    /**
     * Para compras no cartão de alimentação o cliente recebe um desconto de 10%
     */
	private void buyFood(BuyDTO buyDto) {
		Double cashback  = (buyDto.value / DIVIDER_BY_100) * MULTIPLIER_BY_10;
		buyDto.value = buyDto.value - cashback;
		Consumer consumer = repository.findByFoodCardNumber(buyDto.cardNumber);
		consumer.foodCardBalance = consumer.foodCardBalance - buyDto.value;
		repository.save(consumer);
	}
}
