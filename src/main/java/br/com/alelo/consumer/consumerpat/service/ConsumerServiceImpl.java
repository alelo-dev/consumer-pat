package br.com.alelo.consumer.consumerpat.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import br.com.alelo.consumer.consumerpat.exception.BusinessException;
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
	public void updateConsumer(ConsumerDTO dto) throws BusinessException {
		Consumer consumer = new Consumer();
		BeanUtils.copyProperties(dto, consumer);
		repository.save(consumer);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void setBalance(CreditCardDto dto) throws BusinessException {
		
		Consumer consumer = null;
        consumer = repository.findByDrugstoreNumber(dto.getCardNumber());

        if(consumer != null) {
            // é cartão de farmácia
            consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() + dto.getValue().doubleValue());
            repository.save(consumer);
        } else {
            consumer = repository.findByFoodCardNumber(dto.getCardNumber());
            if(consumer != null) {
                // é cartão de refeição
                consumer.setFoodCardBalance(consumer.getFoodCardBalance() + dto.getValue().doubleValue());
                repository.save(consumer);
            } else {
                // É cartão de combustivel
                consumer = repository.findByFuelCardNumber(dto.getCardNumber());
                consumer.setFuelCardBalance(consumer.getFuelCardBalance() + dto.getValue().doubleValue());
                repository.save(consumer);
            }
        }
		
		
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED) 
	public void buy(BuyDTO dto) throws BusinessException {
		 Consumer consumer = null;
	        /* O valores só podem ser debitados dos cartões com os tipos correspondentes ao tipo do estabelecimento da compra.
	        *  Exemplo: Se a compra é em um estabelecimeto de Alimentação(food) então o valor só pode ser debitado do cartão e alimentação
	        *
	        * Tipos de estabelcimentos
	        * 1 - Alimentação (food)
	        * 2 - Farmácia (DrugStore)
	        * 3 - Posto de combustivel (Fuel)
	        */

	        if (dto.getEstablishmentType() == 1) {
	            // Para compras no cartão de alimentação o cliente recebe um desconto de 10%
	            Double cashback  = (dto.getValue() / 100) * 10;
	            dto.setValue(dto.getValue().doubleValue() - cashback);

	            consumer = repository.findByFoodCardNumber(dto.getCardNumber());
	            consumer.setFoodCardBalance(consumer.getFoodCardBalance() - dto.getValue());
	            repository.save(consumer);

	        }else if(dto.getEstablishmentType() == 2) {
	            consumer = repository.findByDrugstoreNumber(dto.getCardNumber());
	            consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() - dto.getValue());
	            repository.save(consumer);

	        } else {
	            // Nas compras com o cartão de combustivel existe um acrescimo de 35%;
	            Double tax  = (dto.getValue() / 100) * 35;
	            dto.setValue(dto.getValue() + tax);

	            consumer = repository.findByFuelCardNumber(dto.getCardNumber());
	            consumer.setFuelCardBalance(consumer.getFuelCardBalance() - dto.getValue());
	            repository.save(consumer);
	        }

	        Extract extract = new Extract(dto.getEstablishmentName(), dto.getProductDescription(), new Date(), dto.getCardNumber(), dto.getValue());
	        extractRepository.save(extract);
		
	}

}
