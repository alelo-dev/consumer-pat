package br.com.alelo.consumer.consumerpat.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import br.com.alelo.consumer.consumerpat.entity.ConsumerCard;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.exception.CardNumberInvalidEstablishmentTypeException;
import br.com.alelo.consumer.consumerpat.exception.CardNumberInvalidException;
import br.com.alelo.consumer.consumerpat.exception.CardTypeInvalidException;
import br.com.alelo.consumer.consumerpat.exception.ConsumerCardNotFoundException;
import br.com.alelo.consumer.consumerpat.exception.ConsumerNotFoundException;
import br.com.alelo.consumer.consumerpat.exception.ValueInvalidException;
import br.com.alelo.consumer.consumerpat.model.enumeration.CardType;
import br.com.alelo.consumer.consumerpat.payload.CardRequest;
import br.com.alelo.consumer.consumerpat.payload.ConsumerCardRequest;
import br.com.alelo.consumer.consumerpat.payload.converter.ConsumerCardConverter;
import br.com.alelo.consumer.consumerpat.respository.ConsumerCardRepository;
import br.com.alelo.consumer.consumerpat.util.AbstractCardBalance;
import br.com.alelo.consumer.consumerpat.util.CardBalanceFactory;


@Service
public class ConsumerCardService {
	
	@Autowired
	private ExtractService extractService;

	@Autowired
	private ConsumerCardRepository consumerCardRepository;

	public void save(final ConsumerCardRequest consumerCardRequest){
		this.consumerCardRepository.saveAndFlush(ConsumerCardConverter.toEntity(consumerCardRequest));
	}
	
	public void addBalanceCardNumber(final CardRequest cardRequest) throws CardNumberInvalidException, ValueInvalidException{
		if(!StringUtils.hasText(cardRequest.getCardNumber())) {
			throw new CardNumberInvalidException();
		}

		if(ObjectUtils.isEmpty(cardRequest.getValue()) || cardRequest.getValue() < 0) {
			throw new ValueInvalidException();
		}

		final ConsumerCard consumerCard = this.findByCardNumber(cardRequest.getCardNumber());
		if(consumerCard != null) {
			consumerCard.setCardBalance(consumerCard.getCardBalance() + cardRequest.getValue()); 
			this.update(consumerCard.getId(), consumerCard);
		}
	}
	
	public void removeBalanceCardNumber(final CardRequest cardRequest) throws CardNumberInvalidEstablishmentTypeException{
		final ConsumerCard consumerCard = this.findByCardNumber(cardRequest.getCardNumber());

		if(consumerCard != null) {
			this.validateCardNumberTypeWithEstablishmentType(cardRequest.getEstablishmentType(), consumerCard);

			final AbstractCardBalance abstractCardBalance = CardBalanceFactory.createFactory(consumerCard.getCardType());
			final Double cardBalanceCalculated = abstractCardBalance.calculateCardBalanceForCardType(consumerCard.getCardBalance(), cardRequest.getValue());

			consumerCard.setCardBalance(cardBalanceCalculated);
			this.update(consumerCard.getId(), consumerCard);

			final Extract extract = Extract.builder()
					.establishmentName(cardRequest.getEstablishmentName())
					.productDescription(cardRequest.getProductDescription())
					.dateBuy(LocalDate.now()) 
					.consumerCard(consumerCard)
					.value(cardRequest.getValue())
					.build();

			this.extractService.save(extract);
		}
	}
	
	private ConsumerCard findById(final Long id){
		return this.consumerCardRepository.findById(id).orElseThrow(() -> new ConsumerNotFoundException());
	}
	
	private ConsumerCard findByCardNumber(final String cardNumber){
		return this.consumerCardRepository.findByCardNumber(cardNumber).orElseThrow(() -> new ConsumerCardNotFoundException());
	}
	
	private void update(final Long id, final ConsumerCard consumerConverted){
		final ConsumerCard consumerCard = this.findById(id);
		consumerCard.setCardBalance(consumerConverted.getCardBalance());
		this.consumerCardRepository.saveAndFlush(consumerCard);
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