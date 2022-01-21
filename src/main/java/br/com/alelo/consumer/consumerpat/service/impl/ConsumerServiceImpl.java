package br.com.alelo.consumer.consumerpat.service.impl;

import static br.com.alelo.consumer.consumerpat.util.Util.isNull;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.exception.ValidacaoException;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;

@Service
public class ConsumerServiceImpl implements ConsumerService{
	
	@Autowired
	private ConsumerRepository consumerRepository;
	
	@Autowired
    ExtractRepository extractRepository;

	@Override
	public Consumer updateBalance(Integer cardNumber, Double value) {
		Consumer consumer = null;
		
		if(isNull(cardNumber)) {
			throw new ValidacaoException("O parâmetro do número do cartão é obrigatório.");
		}
		
		consumer = consumerRepository.findByDrugstoreNumber(cardNumber);
		
		if(consumer != null) {
			consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() + value);
			consumerRepository.save(consumer);
		} else {

			consumer = consumerRepository.findByFoodCardNumber(cardNumber); 
			if(consumer != null) {
				consumer.setFoodCardBalance(consumer.getFoodCardBalance() + value);
				consumerRepository.save(consumer);
			} else {
				consumer = consumerRepository.findByFuelCardNumber(cardNumber);
				if(consumer !=null) {
					consumer.setFuelCardBalance(consumer.getFuelCardBalance() + value);
					consumerRepository.save(consumer);
				}
			}
		}
		
		if(isNull(consumer)) {
			throw new ValidacaoException("O cartão não foi encontrado.");
		}
		
		return consumer;
	}

	@Override
	public Page<Consumer> getAllConsumersList(@PageableDefault(direction = Sort.Direction.ASC,page = 0, size = 100) Pageable pageable) {
		return consumerRepository.findAll(pageable);
	}

	@Override
	public boolean registerConsumer(Consumer consumer) {
		
		Consumer value = consumerRepository.save(consumer);
		
		if(value==null) {
			throw new ValidacaoException("Não foi possível o cadastro de um novo usuário.");
		}
		
		return Boolean.TRUE;
	}

	@Override
	public boolean updateConsumer(Consumer consumer) {
		
		Optional<Consumer> consumerFind = consumerRepository.findById(consumer.getId());
		if(!consumerFind.isPresent()) {
			throw new ValidacaoException("Não foi possível o cadastro de um novo usuário.");
		}
		
		consumer.setFoodCardBalance(consumerFind.get().getFoodCardBalance());
		consumer.setFuelCardBalance(consumerFind.get().getFuelCardBalance());
		consumer.setDrugstoreCardBalance(consumerFind.get().getDrugstoreCardBalance());
		
		Consumer value = consumerRepository.save(consumer);
		
		if(value==null) {
			throw new ValidacaoException("Não foi possível atualizar o usuário.");
		}
		
		return Boolean.TRUE;
	}

	@Override
	public void buy(Integer establishmentType, String establishmentName, Integer cardNumber, String productDescription, Double value) {
		
		Consumer consumer = null;
		
		if (establishmentType == 1) {
            Double cashback  = (value / 100) * 10;
            value = value - cashback;
            
            consumer = consumerRepository.findByFoodCardNumber(cardNumber);
            if(isNull(consumer)) {
    			throw new ValidacaoException("Cartão inválido para o estabelecimento.");
    		}
            consumer.setFoodCardBalance(consumer.getFoodCardBalance() - value);
            consumer = consumerRepository.save(consumer);

        }else if(establishmentType == 2) {
            consumer = consumerRepository.findByDrugstoreNumber(cardNumber);
            if(isNull(consumer)) {
    			throw new ValidacaoException("Cartão inválido para o estabelecimento.");
    		}
            consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() - value);
            consumer = consumerRepository.save(consumer);
        } else {
            Double tax  = (value / 100) * 35;
            value = value + tax;

            consumer = consumerRepository.findByFuelCardNumber(cardNumber);
            if(isNull(consumer)) {
    			throw new ValidacaoException("Cartão inválido para o estabelecimento.");
    		}
            consumer.setFuelCardBalance(consumer.getFuelCardBalance() - value);
            consumer = consumerRepository.save(consumer);
        }
		
		if(consumer==null) {
			throw new ValidacaoException("Falha ao debitar o valor do usuário.");
		}
		
		Extract extract = new Extract(establishmentName, productDescription, new Date(), cardNumber, value);
		extract = extractRepository.save(extract);
        
        if(extract==null) {
			throw new ValidacaoException("Falha ao debitar o valor do estabelecimento.");
		}
	}

}
