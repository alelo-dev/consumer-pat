package br.com.alelo.consumer.consumerpat.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.alelo.consumer.consumerpat.dto.BuyDTO;
import br.com.alelo.consumer.consumerpat.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.dto.CreditCardDto;
import br.com.alelo.consumer.consumerpat.dto.factory.DtoFactory;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.exception.BadCardException;
import br.com.alelo.consumer.consumerpat.exception.BadTypeEstablishmentException;
import br.com.alelo.consumer.consumerpat.exception.BusinessException;
import br.com.alelo.consumer.consumerpat.exception.ConsumerNotFoundException;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;

@Service
public class ConsumerServiceImpl implements ConsumerService {

	@Autowired
	private ConsumerRepository repository;
	
	@Autowired
	private ExtractRepository extractRepository;
	
	@Override
	public List<ConsumerDTO> getAllConsumersList() {
		return repository.findAll().stream().map(DtoFactory::builder).collect(Collectors.toCollection(ArrayList::new));
	}

	@Override
	public void createConsumer(ConsumerDTO dto) throws BusinessException {
		Consumer consumer = new Consumer();
		BeanUtils.copyProperties(dto, consumer);
		repository.save(consumer);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateConsumer(ConsumerDTO dto,Integer id) throws BusinessException,ConsumerNotFoundException {
		Optional<Consumer> consumerBD = repository.findById(id);
		if (consumerBD.isEmpty() || !consumerBD.isPresent()) {
			throw new ConsumerNotFoundException();
		}	
		try {
			Integer idBd = consumerBD.get().getId();
			BeanUtils.copyProperties(dto, consumerBD.get(),"foodCardBalance","fuelCardBalance","drugstoreCardBalance");
			consumerBD.get().setId(idBd);
			repository.save(consumerBD.get());
		}
		catch(Exception e) {
			throw new BusinessException();
		}	
	}

	@SuppressWarnings("unused")
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void setBalance(CreditCardDto dto) throws BusinessException,BadCardException {
		Consumer consumer = prepareCreditCardToSave(dto);
		if (consumer == null) {
			throw new BadCardException();
		}	
		else {
			try {
				repository.save(consumer);
			}
			catch(Exception e) {
				throw new BusinessException();
			}
			
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED) 
	public void buy(BuyDTO dto) throws BusinessException,BadTypeEstablishmentException {
		
        /* O valores só podem ser debitados dos cartões com os tipos correspondentes ao tipo do estabelecimento da compra.
        *  Exemplo: Se a compra é em um estabelecimeto de Alimentação(food) então o valor só pode ser debitado do cartão e alimentação
        *
        * Tipos de estabelcimentos
        * 1 - Alimentação (food)
        * 2 - Farmácia (DrugStore)
        * 3 - Posto de combustivel (Fuel)
        */

		Consumer consumer = prepareConsumerBuyToSave(dto);
		
		
		if (consumer == null) {
			throw new BadTypeEstablishmentException();
		}
		
		try {
			repository.save(consumer);
			
	        Extract extract = new Extract(dto.getEstablishmentName(),dto.getEstablishmentNameId(),  dto.getProductDescription(), new Date(), dto.getCardNumber(), dto.getValue());
	        extractRepository.save(extract);
		}
		catch(Exception e) {
			throw new BusinessException();
		}
			
		
	}

	
	private Consumer prepareCreditCardToSave(CreditCardDto dto) {
        
		Consumer consumer = repository.findByDrugstoreNumber(dto.getCardNumber());
		
		if(consumer != null) {
            // é cartão de farmácia
            consumer.setDrugStoreCardBalance(consumer.getDrugStoreCardBalance() + dto.getValue().doubleValue());
        } else {
            consumer = repository.findByFoodCardNumber(dto.getCardNumber());
            if(consumer != null) {
                // é cartão de refeição
                consumer.setFoodCardBalance(consumer.getFoodCardBalance() + dto.getValue().doubleValue());
            } else {
                // É cartão de combustivel
                consumer = repository.findByFuelCardNumber(dto.getCardNumber());
                if (consumer!=null) {
                    consumer.setFuelCardBalance(consumer.getFuelCardBalance() + dto.getValue().doubleValue());
                }
            }
        }
		return consumer;

	}

	private Consumer prepareConsumerBuyToSave(BuyDTO dto) throws BusinessException,	BadTypeEstablishmentException
    {
		Consumer consumer = null;
	
		
		List<Integer> typesEstablishment =new ArrayList<Integer>(){{
            add(1);
            add(2);
            add(3);
         }};
		
		if (!typesEstablishment.contains(dto.getEstablishmentType())) {
			throw new BadTypeEstablishmentException();
		}
         
		try {
			if (dto.getEstablishmentType() == 1) {
	            // Para compras no cartão de alimentação o cliente recebe um desconto de 10%
	            Double cashback  = (dto.getValue() / 100) * 10;
	            dto.setValue(dto.getValue().doubleValue() - cashback);

	            consumer = repository.findByFoodCardNumber(dto.getCardNumber());
	            if (consumer!=null) {
		            consumer.setFoodCardBalance(consumer.getFoodCardBalance() - dto.getValue());
	            }

	        }else if(dto.getEstablishmentType() == 2) {
	            consumer = repository.findByDrugstoreNumber(dto.getCardNumber());
	            if (consumer!=null) {
		            consumer.setDrugStoreCardBalance(consumer.getDrugStoreCardBalance() - dto.getValue());
	            }

	        } else {
	            // Nas compras com o cartão de combustivel existe um acrescimo de 35%;
	            Double tax  = (dto.getValue() / 100) * 35;
	            dto.setValue(dto.getValue() + tax);
	            
	            consumer = repository.findByFuelCardNumber(dto.getCardNumber());
	            if(consumer!=null) {
	                consumer.setFuelCardBalance(consumer.getFuelCardBalance() - dto.getValue());
	            }
	        }
	        
			return consumer;
		}
		
		catch(Exception e) {
			throw new BusinessException();
		}
		
	}
	
	
	
}
