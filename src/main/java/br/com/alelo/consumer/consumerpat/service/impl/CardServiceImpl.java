package br.com.alelo.consumer.consumerpat.service.impl;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.enums.OperationType;
import br.com.alelo.consumer.consumerpat.exceptions.CardAndEstablishmentTypeInvalidException;
import br.com.alelo.consumer.consumerpat.exceptions.CardNotFoundException;

import br.com.alelo.consumer.consumerpat.exceptions.CardTypeEnumsNotFoundException;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.CardService;
import lombok.AllArgsConstructor;

import static br.com.alelo.consumer.consumerpat.enums.CardType.*;
import java.time.LocalDateTime;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CardServiceImpl implements CardService {

	private final CardRepository cardRepository;
	private final ExtractRepository extractRepository;

	@Override
	@Transactional
	public void setBalance(String cardNumber, Double value) {
		Card card = cardRepository.findCardByNumber(cardNumber).orElseThrow(CardNotFoundException::new);

		extractRepository
				.save(Extract.builder().cardNumber(card.getCardNumber()).operationType(OperationType.CREDIT.getValue())
						.dateOperation(LocalDateTime.now()).value(value).build());

		card.setCardBalance(card.getCardBalance() + value);
		cardRepository.save(card);
	}
	@Override
	public void buy(Integer establishmentType, String establishmentName, String cardNumber, String productDescription,
			Double value) {
		Card card = cardRepository.findCardByNumber(cardNumber).orElseThrow(CardNotFoundException::new);

		if (card.getCarType().equals(establishmentType)) {
			switch (getEnum(card.getCarType())) {
			case FOOD:
				card.setCardBalance(card.getCardBalance() - (value - (value * 0.1)));
				cardRepository.save(card);
				break;
			case DRUGSTORE:
				card.setCardBalance(card.getCardBalance() - value);
				cardRepository.save(card);
				break;
			case FUEL:
				card.setCardBalance(card.getCardBalance() - (value + (value * 0.35)));
				cardRepository.save(card);
				break;
			default:
				throw new CardTypeEnumsNotFoundException();
			}

			extractRepository.save(Extract.builder().value(value).dateOperation(LocalDateTime.now())
					.operationType(OperationType.DEBIT.getValue()).cardNumber(card.getCardNumber())
					.establishmentName(establishmentName).establishmentType(establishmentType)
					.productDescription(productDescription).build());
		} else {
			throw new CardAndEstablishmentTypeInvalidException();
		}

	}

}
