package br.com.alelo.consumer.consumerpat.service;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import br.com.alelo.consumer.consumerpat.entity.ConsumerCard;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.enumeration.CardType;
import br.com.alelo.consumer.consumerpat.exception.CardNumberInvalidEstablishmentTypeException;
import br.com.alelo.consumer.consumerpat.exception.CardNumberInvalidException;
import br.com.alelo.consumer.consumerpat.exception.CardTypeInvalidException;
import br.com.alelo.consumer.consumerpat.exception.ConsumerCardNotFoundException;
import br.com.alelo.consumer.consumerpat.exception.ConsumerNotFoundException;
import br.com.alelo.consumer.consumerpat.exception.ValueInvalidException;
import br.com.alelo.consumer.consumerpat.factory.AbstractCardBalance;
import br.com.alelo.consumer.consumerpat.factory.CardBalanceFactory;
import br.com.alelo.consumer.consumerpat.payload.CardRequest;
import br.com.alelo.consumer.consumerpat.payload.ConsumerCardRequest;
import br.com.alelo.consumer.consumerpat.payload.converter.ConsumerCardConverter;
import br.com.alelo.consumer.consumerpat.repository.ConsumerCardRepository;
import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class ConsumerCardService {
	
	private final ExtractService extractService;

	private final ConsumerCardRepository consumerCardRepository;

	public void save(final ConsumerCardRequest consumerCardRequest){
		this.consumerCardRepository.saveAndFlush(ConsumerCardConverter.toEntity(consumerCardRequest));
	}
	
	public void addBalanceCardNumber(final String cardNumber, final CardRequest cardRequest){
		if(!StringUtils.hasText(cardNumber)) {
			throw new CardNumberInvalidException();
		}

		if(ObjectUtils.isEmpty(cardRequest.getValue()) || cardRequest.getValue().compareTo(BigDecimal.ZERO) < 0) {
			throw new ValueInvalidException();
		}

		final ConsumerCard consumerCard = this.findByCardNumber(cardNumber);
		if(consumerCard != null) {
			consumerCard.setCardBalance(consumerCard.getCardBalance().add(cardRequest.getValue())); 
			this.update(consumerCard.getId(), consumerCard);
		}
	}
	
	public void removeBalanceCardNumber(final String cardNumber, final CardRequest cardRequest){
		if(!StringUtils.hasText(cardNumber)) {
			throw new CardNumberInvalidException();
		}
		
		final ConsumerCard consumerCard = this.findByCardNumber(cardNumber);

		if(consumerCard != null) {
			this.validateCardNumberTypeWithEstablishmentType(cardRequest.getEstablishmentType(), consumerCard);

			final AbstractCardBalance abstractCardBalance = CardBalanceFactory.createFactory(consumerCard.getCardType());
			final BigDecimal cardBalanceCalculated = abstractCardBalance.calculateCardBalanceForCardType(consumerCard.getCardBalance(), cardRequest.getValue());

			consumerCard.setCardBalance(cardBalanceCalculated);
			this.update(consumerCard.getId(), consumerCard);

			final Extract extract = Extract.builder()
					.establishmentNameId(cardRequest.getEstablishmentType())
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
	
	private void validateCardNumberTypeWithEstablishmentType(final CardType establishmentType, final ConsumerCard consumerCard) {
		if(establishmentType == null) {
			throw new CardTypeInvalidException();
		}

		if(!consumerCard.getCardType().equals(establishmentType)) {
			throw new CardNumberInvalidEstablishmentTypeException("This card number: " + consumerCard.getCardNumber() + " can only be used in " + consumerCard.getCardType().name().toLowerCase() + " establishments!");
		}
	}

}