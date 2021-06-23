package br.com.alelo.consumer.consumerpat.service;


import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import br.com.alelo.consumer.consumerpat.exception.CardNumberInvalidEstablishmentTypeException;
import br.com.alelo.consumer.consumerpat.exception.CardNumberInvalidException;
import br.com.alelo.consumer.consumerpat.exception.CardTypeInvalidException;
import br.com.alelo.consumer.consumerpat.exception.ConsumerNotFoundException;
import br.com.alelo.consumer.consumerpat.exception.ValueInvalidException;
import br.com.alelo.consumer.consumerpat.model.converter.ConsumerCardConverter;
import br.com.alelo.consumer.consumerpat.model.converter.ConsumerConverter;
import br.com.alelo.consumer.consumerpat.model.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.model.dto.ExtractDTO;
import br.com.alelo.consumer.consumerpat.model.entity.Consumer;
import br.com.alelo.consumer.consumerpat.model.entity.ConsumerCard;
import br.com.alelo.consumer.consumerpat.model.enumeration.CardType;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.util.AbstractCardBalance;
import br.com.alelo.consumer.consumerpat.util.CardBalanceFactory;

@Service
public class ConsumerService {

	@Autowired
	private ExtractService extractService;

	@Autowired
	private ConsumerCardService consumerCardService; 

	@Autowired
	private ConsumerRepository consumerRepository;

	public ConsumerDTO findConsumerDTOById(final Long id){
		final Consumer consumer = this.consumerRepository.findById(id).orElseThrow(() -> new ConsumerNotFoundException());
		return ConsumerConverter.toDTO(consumer);
	}

	public PageImpl<ConsumerDTO> findAll(final Pageable pageable){
		final Page<Consumer> pageConsumer = this.consumerRepository.findAll(pageable);
		return new PageImpl<>(ConsumerConverter.toListConsumerDTO(pageConsumer.getContent()));
	}

	public void save(final ConsumerDTO dto){
		this.consumerRepository.save(ConsumerConverter.toEntity(dto));
	}

	public void update(final Long id, final ConsumerDTO dto){
		final Consumer consumer = this.findById(id);
		final Consumer consumerConverted = ConsumerConverter.toEntity(dto);
		consumer.merge(consumerConverted);
		this.consumerRepository.save(consumer);
	}

	public void delete(final Long id){
		final Consumer consumer = this.findById(id);
		this.consumerRepository.delete(consumer);
	}

	public void addBalanceCardNumber(final String cardNumber, final Double value) throws CardNumberInvalidException, ValueInvalidException{
		if(!StringUtils.hasText(cardNumber)) {
			throw new CardNumberInvalidException();
		}

		if(ObjectUtils.isEmpty(value) || value < 0) {
			throw new ValueInvalidException();
		}

		final ConsumerCard consumerCard = this.consumerCardService.findByCardNumber(cardNumber);
		if(consumerCard != null) {
			consumerCard.setCardBalance(consumerCard.getCardBalance() + value); 
			this.consumerCardService.update(consumerCard.getId(), ConsumerCardConverter.toDTO(consumerCard));
		}
	}

	public void removeBalanceCardNumber(final Integer establishmentType, final String establishmentName, final String cardNumber, final String productDescription, final Double value) throws CardNumberInvalidEstablishmentTypeException{
		final ConsumerCard consumerCard = this.consumerCardService.findByCardNumber(cardNumber);

		if(consumerCard != null) {
			this.validateCardNumberTypeWithEstablishmentType(establishmentType, consumerCard);

			final AbstractCardBalance abstractCardBalance = CardBalanceFactory.createFactory(consumerCard.getCardType());
			final Double cardBalanceCalculated = abstractCardBalance.calculateCardBalanceForCardType(consumerCard.getCardBalance(), value);

			consumerCard.setCardBalance(cardBalanceCalculated);
			this.consumerCardService.update(consumerCard.getId(), ConsumerCardConverter.toDTO(consumerCard));

			final ExtractDTO extractDTO = ExtractDTO.builder()
					.establishmentName(establishmentName)
					.productDescription(productDescription)
					.dateBuy(LocalDate.now()) 
					.consumerCard(ConsumerCardConverter.toDTO(consumerCard))
					.value(value)
					.build();

			this.extractService.save(extractDTO);

		}

	}

	private Consumer findById(final Long id){
		return this.consumerRepository.findById(id).orElseThrow(() -> new ConsumerNotFoundException());
	}

	private void validateCardNumberTypeWithEstablishmentType(final Integer establishmentType, final ConsumerCard consumerCard) {
		final CardType cardType = CardType.getById(establishmentType);

		if(cardType == null) {
			throw new CardTypeInvalidException();
		}

		if(!consumerCard.getCardType().equals(cardType)) {
			throw new CardNumberInvalidEstablishmentTypeException("This card number: " + consumerCard.getCardNumber() + " can only be used in " + consumerCard.getCardType().name().toLowerCase() + " establishments!");
		}
	}

}