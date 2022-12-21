package br.com.alelo.consumer.consumerpat.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.exception.BusinessRulesException;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;

@Service
public class ConsumerService {
	
	@Autowired
    private ConsumerRepository consumerRepository;

    @Autowired
    private ExtractRepository extractRepository;
    
    public List<Consumer> listAll() {
        return consumerRepository.findAll();
    }
    
    public Consumer create(Consumer consumer) {
    	validateDuplicateConsumer(consumer);
        return consumerRepository.save(consumer);
    }
        
    private void validateDuplicateConsumer(Consumer consumer) {
    	Consumer consumerBy = consumerRepository.findByDocumentNumber(consumer.getDocumentNumber());
        if (consumerBy != null && consumerBy.getId() != consumer.getId()) {
            throw new BusinessRulesException(
                    String.format("The consumer %s is already created", consumer.getName().toUpperCase()));
        }
    }
    
    // Não deve ser possível alterar o saldo do cartão
	public Consumer update(Integer id, Consumer consumerToUpdate) {
		Consumer consumer = validateHasConsumer(id);
		validateCardBalance(consumer, consumerToUpdate);
		BeanUtils.copyProperties(consumerToUpdate, consumer, "id");
        return consumerRepository.save(consumer);
	}
	
	private Consumer validateHasConsumer(Integer id) {
    	Optional<Consumer> consumer = consumerRepository.findById(id);
        if(consumer.isEmpty()){
            throw new EmptyResultDataAccessException(1);
        }
        return consumer.get();
	}
    
    private void validateCardBalance(Consumer consumer, Consumer consumerToUpdate) {
        if (consumerToUpdate.getFoodCardBalance() != consumer.getFoodCardBalance() ||
        		consumerToUpdate.getDrugstoreCardBalance() != consumer.getDrugstoreCardBalance() ||
        		consumerToUpdate.getFuelCardBalance() != consumer.getFuelCardBalance()) {
            throw new BusinessRulesException("The balance card can't be changed");
        }
    }

    /* O valores só podem ser creditados dos cartões com os tipos correspondentes ao tipo do benefício.
     *
     * Tipos de benefícioa
     * 1 - Alimentação (food)
     * 2 - Farmácia (DrugStore)
     * 3 - Posto de combustivel (Fuel)
     */
	public Consumer updateCardBalance(int cardType, int cardNumber, double value) {
		Consumer consumer = null;
		switch (cardType) {
			case 1:
				consumer = consumerRepository.findByFoodCardNumber(cardNumber);
				break;
			case 2:
				consumer = consumerRepository.findByDrugstoreNumber(cardNumber);
				break;
			case 3:
				consumer = consumerRepository.findByFuelCardNumber(cardNumber);
				break;
		}
		if(consumer == null) {
			throw new BusinessRulesException("Invalid card number.");
		}
		return consumerRepository.save(consumer);
	}

	/* O valores só podem ser debitados dos cartões com os tipos correspondentes ao tipo do estabelecimento da compra.
     *  Exemplo: Se a compra é em um estabelecimeto de Alimentação(food) então o valor só pode ser debitado do cartão e alimentação
     *
     * Tipos de estabelcimentos
     * 1 - Alimentação (food)
     * 2 - Farmácia (DrugStore)
     * 3 - Posto de combustivel (Fuel)
     */
	public Consumer buy(int establishmentType, String establishmentName, int cardNumber, String productDescription,
			double value) {
		Consumer consumer = null;
		switch (establishmentType) {
			case 1:
				// Para compras no cartão de alimentação o cliente recebe um desconto de 10%
			    Double cashback  = (value / 100) * 10;
			    value = value - cashback;
			    
			    consumer = consumerRepository.findByFoodCardNumber(cardNumber);
			    consumer.setFoodCardBalance(consumer.getFoodCardBalance() - value);
				break;
			case 2:
				consumer = consumerRepository.findByDrugstoreNumber(cardNumber);
				consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() - value);
				break;
			case 3:
				// Nas compras com o cartão de combustivel existe um acrescimo de 35%;
			    Double tax  = (value / 100) * 35;
			    value = value + tax;

			    consumer = consumerRepository.findByFuelCardNumber(cardNumber);
			    consumer.setFuelCardBalance(consumer.getFuelCardBalance() - value);			    
				break;
		}
		if(consumer == null) {
			throw new BusinessRulesException("Invalid establishment type or Invalid card number.");
		}
        Extract extract = new Extract(establishmentName, productDescription, new Date(), cardNumber, value);
        extractRepository.save(extract);
        return consumerRepository.save(consumer);
	}
}
