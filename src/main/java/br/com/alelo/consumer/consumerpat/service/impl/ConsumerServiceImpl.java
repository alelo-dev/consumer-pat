package br.com.alelo.consumer.consumerpat.service.impl;

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
import br.com.alelo.consumer.consumerpat.entity.EstablishmentType;
import br.com.alelo.consumer.consumerpat.exception.ValidacaoException;
import br.com.alelo.consumer.consumerpat.request.BuyRequest;
import br.com.alelo.consumer.consumerpat.request.UpdateBalanceRequest;
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
	public Consumer updateBalance(UpdateBalanceRequest updateBalance) {
		Optional<Consumer> consumer = null;
		
		consumer = consumerRepository.findByDrugstoreNumber(updateBalance.getCardNumber());
		
		if(consumer.isPresent() ) {
			consumer.get().setDrugstoreCardBalance(consumer.get().getDrugstoreCardBalance() + updateBalance.getValue());
			consumerRepository.save(consumer.get());
		} else {

			consumer = consumerRepository.findByFoodCardNumber(updateBalance.getCardNumber()); 
			if(consumer != null) {
				consumer.get().setFoodCardBalance(consumer.get().getFoodCardBalance() + updateBalance.getValue());
				consumerRepository.save(consumer.get());
			} else {
				consumer = consumerRepository.findByFuelCardNumber(updateBalance.getCardNumber());
				if(consumer !=null) {
					consumer.get().setFuelCardBalance(consumer.get().getFuelCardBalance() + updateBalance.getCardNumber());
					consumerRepository.save(consumer.get());
				}
			}
		}
		
		if(!consumer.isPresent()) 
			throw new ValidacaoException("O cartão não foi encontrado.");
		
		return consumer.get();
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
	public void buy(BuyRequest buyRequest) {
		
		Consumer consumer = null;
				
		if (buyRequest.getTypeEstablishment().equals(EstablishmentType.FOOD) ) {
            Double cashback  = (buyRequest.getValue() / 100) * 10;
            buyRequest.setValue(buyRequest.getValue() - cashback);
            
            consumer = consumerRepository.findByFoodCardNumber(buyRequest.getCardNumber()).orElseThrow(ValidacaoException::new);
            consumer.setFoodCardBalance(consumer.getFoodCardBalance() - buyRequest.getValue());
            consumer = consumerRepository.save(consumer);

        }else if(buyRequest.getTypeEstablishment().equals(EstablishmentType.DRUGSTORE) ) {
            consumer = consumerRepository.findByDrugstoreNumber(buyRequest.getCardNumber()).orElseThrow(ValidacaoException::new);
            consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() - buyRequest.getValue());
            consumer = consumerRepository.save(consumer);
        } else {
            Double tax  = (buyRequest.getValue() / 100) * 35;
            buyRequest.setValue(buyRequest.getValue()+tax);
            
            consumer = consumerRepository.findByFuelCardNumber(buyRequest.getCardNumber()).orElseThrow(ValidacaoException::new);
            consumer.setFuelCardBalance(consumer.getFuelCardBalance() - buyRequest.getValue());
            consumer = consumerRepository.save(consumer);
        }
		
		if(consumer==null) {
			throw new ValidacaoException("Falha ao debitar o valor do usuário.");
		}
		
		Extract extract = new Extract(buyRequest.getEstablishmentName(), buyRequest.getProductDescription(), new Date(), buyRequest.getCardNumber(), buyRequest.getValue());
		extract = extractRepository.save(extract);
        
        if(extract==null) 
			throw new ValidacaoException("Falha ao debitar o valor do estabelecimento.");
	}

}
