package br.com.alelo.consumer.consumerpat.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alelo.consumer.consumerpat.exception.UnsupportedOperationException;
import br.com.alelo.consumer.consumerpat.model.BusinessType;
import br.com.alelo.consumer.consumerpat.model.Card;
import br.com.alelo.consumer.consumerpat.model.Establishment;
import br.com.alelo.consumer.consumerpat.model.Extract;
import br.com.alelo.consumer.consumerpat.repository.CardRepository;
import br.com.alelo.consumer.consumerpat.repository.EstablishmentRepository;
import br.com.alelo.consumer.consumerpat.repository.ExtractRepository;

@Service
public class OperationService {
	private static final Logger log = LoggerFactory.getLogger(ConsumerService.class);

	private CardRepository cardRepository;
	private EstablishmentRepository establishmentRepository;
	private ExtractRepository extractRepository;

	@Autowired
	public OperationService(CardRepository cardRepository, ExtractRepository extractRepository,
			EstablishmentRepository establishmentRepository) {
		this.cardRepository = cardRepository;
		this.extractRepository = extractRepository;
		this.establishmentRepository = establishmentRepository;
	}

	/**
	 * Efetuar operação de crédito em um cartão
	 * 
	 * @param cardNumber
	 * @param value
	 * @return
	 */
	@Transactional
	public Card carryOutCredit(String cardNumber, BigDecimal value) {
		if(BigDecimal.ZERO.compareTo(value) >= 0) {
			log.error("Invalid value to make a credit operation.");
			throw new UnsupportedOperationException("Invalid value. Operation aborted.");
		}
			
		try {
			final Card identifiedCard = cardRepository.findByNumber(cardNumber);
			if (identifiedCard != null) {
				identifiedCard.credit(value);
				return cardRepository.saveAndFlush(identifiedCard);
			}
		} catch (Exception e) {
			log.error("Error making a credit operation.", e);
			throw new UnsupportedOperationException("Error making a credit operation. Operation aborted.",
					e);
		}
		throw new UnsupportedOperationException("Not identified card. Operation aborted.");
	}

	/**
	 * Efetuando operação de débito em um cartão
	 * 
	 * @param establishmentType
	 * @param establishmentName
	 * @param cardNumber
	 * @param productDescription
	 * @param value
	 * @return
	 */
	@Transactional
	public Extract carryOutDebit(final int establishmentType, final String establishmentName, final String cardNumber,
			final String productDescription, final BigDecimal value) {
		if(BigDecimal.ZERO.compareTo(value) >= 0) {
			log.error("Invalid value to make a debit operation.");
			throw new UnsupportedOperationException("Invalid value. Operation aborted.");
		}
		final BusinessType businessType;
		if (!BusinessType.getBusinessTypeById(establishmentType).isPresent()) {
			log.error("Unknow business type.");
			throw new UnsupportedOperationException("Unknown business type. Operation aborted.");
		} else {
			businessType = BusinessType.getBusinessTypeById(establishmentType).get();
		}

		final LocalDateTime dateBuy = LocalDateTime.now(); // apenas para teste, não verificando timezone...

		final Card identifiedCard = identifyCard(cardNumber);
		
		if (identifiedCard.getType() != businessType) {
			log.error("Card type not compatible with the Establishment business type.");
			throw new UnsupportedOperationException(
					"Card type not compatible with the Establishment business type. Operation aborted.");
		}

		final Establishment identifiedEstablishment = identifyEstablishment(establishmentName, businessType);

		final BigDecimal totalDebitAmount;
		
		switch (businessType) {
			case FOOD:
				totalDebitAmount = applyDiscount(value, 10.00);
				break;
			case FUEL:
				totalDebitAmount = applyTax(value, 35.00);
				break;
			default:
				totalDebitAmount = value;
				break;
		}
		
		if (identifiedCard.getBalance().compareTo(totalDebitAmount.setScale(2, RoundingMode.HALF_UP)) < 0) {
			log.error("Not enough balance to make a debit operation.");
			throw new UnsupportedOperationException("Not enough balance to make a debit operation. Operation aborted.");
		}
		try {
			identifiedCard.debit(totalDebitAmount.setScale(2, RoundingMode.HALF_UP));
			cardRepository.save(identifiedCard);
			
			final Extract extract = new Extract(identifiedEstablishment, productDescription, dateBuy, identifiedCard, value);		
			return extractRepository.save(extract);
		}catch(Exception e) {
			log.error("Error carrying out a debit operation.", e);
			throw new UnsupportedOperationException("Error carrying out a debit operation. Operation aborted.", e);
		}
	}

	private Card identifyCard(String number) {
		final Card identifiedCard = cardRepository.findByNumber(number);
		if (identifiedCard == null) {
			log.error("Not identified card.");
			throw new UnsupportedOperationException("Not identified card. Operation aborted.");
		}
		return identifiedCard;
	}

	private Establishment identifyEstablishment(String name, BusinessType type) {
		final Establishment establishment;
		try {
			establishment = establishmentRepository.findByNameAndType(name, type).get(0);
			if (establishment == null) { // poderia inserir o estabelecimento...
				log.error("Not identified establishment.");
				throw new UnsupportedOperationException("Not identified establishment. Operation aborted.");
			}			
			return establishment;
		}catch(Exception e) {
			log.error(e.getMessage(), e.getCause());
			throw new UnsupportedOperationException("Not identified establishment. Operation aborted.");
		}
	}

	private static BigDecimal applyDiscount(BigDecimal value, double percentage) {
		final BigDecimal percentageAmount = value.multiply(BigDecimal.valueOf(percentage / 100.00));
		final BigDecimal result = value.subtract(percentageAmount);
		return result.setScale(2, RoundingMode.HALF_UP);
	}

	private static BigDecimal applyTax(BigDecimal value, double percentage) {
		final BigDecimal percentageAmount = value.multiply(BigDecimal.valueOf(percentage / 100.00));
		final BigDecimal result = value.add(percentageAmount);
		return result.setScale(2, RoundingMode.HALF_UP);
	}

}
