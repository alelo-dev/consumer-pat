package br.com.alelo.consumer.consumerpat.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.enums.EstablishmentType;
import br.com.alelo.consumer.consumerpat.exception.BusinessException;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;


@Service
public class ConsumerService {
	
	private final static String MSG_INVALID_CARD_NUMBER = "Número de cartão inválido"; 
	private final static String MSG_INVALID_ESTABLISHMENT_TYPE = "Tipo de estabelecimento invalido"; 

    @Autowired
    ConsumerRepository repository;

    @Autowired
    ExtractRepository extractRepository;


    /* Deve listar todos os clientes (cerca de 500) */
    public List<Consumer> listAllConsumers() {
        return repository.findAll();
    }


    /* Salvar clientes */
    public Consumer saveConsumer(Consumer consumer) {
        return repository.save(consumer);
    }

    public Optional<Consumer> findById(int idConsumer) {
        return repository.findById(idConsumer);
    }

    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
    public Consumer setBalance(int cardNumber, double value) throws BusinessException {
        Consumer consumer = null;
        Optional<Consumer> consumerOpt;
        consumerOpt = repository.findByDrugstoreNumber(cardNumber);

        if(consumerOpt.isPresent()) {
            // é cartão de farmácia
        	consumer = consumerOpt.get();
            consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() + value);
            repository.save(consumer);
        } else {
        	consumerOpt = repository.findByFoodCardNumber(cardNumber);
            if(consumerOpt.isPresent()) {
                // é cartão de refeição
            	consumer = consumerOpt.get();
                consumer.setFoodCardBalance(consumer.getFoodCardBalance() + value);
                repository.save(consumer);
            } else {
            	consumerOpt = repository.findByFuelCardNumber(cardNumber);
            	if(consumerOpt.isPresent()) {
            		// É cartão de combustivel
            		consumer = consumerOpt.get();
	                consumer.setFuelCardBalance(consumer.getFuelCardBalance() + value);
	                repository.save(consumer);
            	}else {
            		throw new BusinessException(MSG_INVALID_CARD_NUMBER);
            	}
            }
        }
        return consumer;
    }

    public Extract buy(int establishmentType, String establishmentName, int cardNumber, String productDescription, double value) throws BusinessException {
        Consumer consumer = null;
        /* O valores só podem ser debitados dos cartões com os tipos correspondentes ao tipo do estabelecimento da compra.
        *  Exemplo: Se a compra é em um estabelecimeto de Alimentação(food) então o valor só pode ser debitado do cartão e alimentação
        *
        * Tipos de estabelcimentos
        * 1 - Alimentação (food)
        * 2 - Farmácia (DrugStore)
        * 3 - Posto de combustivel (Fuel)
        */

        EstablishmentType establishmentTypeEnum = EstablishmentType.fromInt(establishmentType);
        if (establishmentTypeEnum == EstablishmentType.FOOD_ESTAB) {
            // Para compras no cartão de alimentação o cliente recebe um desconto de 10%
            Double cashback  = (value / 100) * 10;
            value = value - cashback;

            Optional<Consumer> consumerOpt = repository.findByFoodCardNumber(cardNumber);
            if(consumerOpt.isPresent()) {
            	consumer = consumerOpt.get();
            	consumer.setFoodCardBalance(consumer.getFoodCardBalance() - value);
            	repository.save(consumer);
            }else {
            	throw new BusinessException(MSG_INVALID_CARD_NUMBER);
            }

        }else if(establishmentTypeEnum == EstablishmentType.DRUGSTORE_ESTAB) {
        	Optional<Consumer> consumerOpt = repository.findByDrugstoreNumber(cardNumber);
        	if(consumerOpt.isPresent()) {
            	consumer = consumerOpt.get();
	            consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() - value);
	            repository.save(consumer);
        	}else {
            	throw new BusinessException(MSG_INVALID_CARD_NUMBER);
            }

        }else if(establishmentTypeEnum == EstablishmentType.FUEL_ESTAB) {
            // Nas compras com o cartão de combustivel existe um acrescimo de 35%;
            Double tax  = (value / 100) * 35;
            value = value + tax;

            Optional<Consumer> consumerOpt = repository.findByFuelCardNumber(cardNumber);
            if(consumerOpt.isPresent()) {
            	consumer = consumerOpt.get();
	            consumer.setFuelCardBalance(consumer.getFuelCardBalance() - value);
	            repository.save(consumer);
            }else {
            	throw new BusinessException(MSG_INVALID_CARD_NUMBER);
            }
        } else {
        	throw new BusinessException(MSG_INVALID_ESTABLISHMENT_TYPE);
        }

        Extract extract = new Extract(establishmentName, productDescription, LocalDate.now(), cardNumber, value);
        return extractRepository.save(extract);
    }

}
