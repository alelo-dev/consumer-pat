package br.com.alelo.consumer.consumerpat.service.impl;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.error.BusinessError;
import br.com.alelo.consumer.consumerpat.exception.BusinessException;
import br.com.alelo.consumer.consumerpat.model.Card;
import br.com.alelo.consumer.consumerpat.model.Extract;
import br.com.alelo.consumer.consumerpat.model.dto.CardDTO;
import br.com.alelo.consumer.consumerpat.model.dto.CardValueTransactionDTO;
import br.com.alelo.consumer.consumerpat.model.dto.EstablishmentDTO;
import br.com.alelo.consumer.consumerpat.model.dto.ExtractDTO;
import br.com.alelo.consumer.consumerpat.model.dto.RequestBuyDTO;
import br.com.alelo.consumer.consumerpat.model.enums.TypeCard;
import br.com.alelo.consumer.consumerpat.model.mapper.CardMapper;
import br.com.alelo.consumer.consumerpat.model.mapper.ExtractMapper;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.CardService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CardServiceImpl implements CardService {

	private CardMapper cardMapper;

	private CardRepository cardRepository;

	private ExtractRepository extractRepository;
	
	private ExtractMapper extractMapper;

	@Autowired
	public CardServiceImpl(CardRepository cardRepository, CardMapper cardMapper, ExtractRepository extractRepository, ExtractMapper extractMapper) {
		this.cardRepository = cardRepository;
		this.cardMapper = cardMapper;
		this.extractRepository = extractRepository;
		this.extractMapper = extractMapper;
	}

	@Override
	@Transactional
	public CardDTO setBalance(CardValueTransactionDTO creditCardDTO) {

		log.info("### class: CardServiceImpl| Method: setBalance");
		Card card = findByCardNumber(creditCardDTO.getCardNumber());

		card.setBalance(card.getBalance() + creditCardDTO.getValue());

		Card savedCard = cardRepository.save(card);

		return cardMapper.toDTO(savedCard);
	}

	@Override
	@Transactional
	public Card findByCardNumber(Integer cardNumber) {
		return cardRepository.findByCardNumber(cardNumber)
				.orElseThrow(() -> new BusinessException(BusinessError.RESOURCE_NOT_FOUND));
	}

	@Override
	@Transactional
	public ExtractDTO buyProductWithCard(RequestBuyDTO requestBuyDTO) {

		Integer cardNumber = requestBuyDTO.getCardValueTransaction().getCardNumber();
		TypeCard authorizedCard = requestBuyDTO.getEstablishment().getAuthorizedCard();
		Double value = requestBuyDTO.getCardValueTransaction().getValue();

		Card card = cardRepository
				.findByCardNumberAndTypeCardAndBalanceGreaterThanEqual(cardNumber, authorizedCard, value)
				.orElseThrow(() -> new BusinessException(BusinessError.UNAUTHORIZED_CARD_TRANSACTION));

		Double finalValue = calcValue(authorizedCard, value);

		return persistTransactionAndCreateExtract(finalValue, card, requestBuyDTO.getEstablishment(),
				requestBuyDTO.getProductDescription());
	}

	private Double calcValue(TypeCard requestTypeCard, Double value) {
		return requestTypeCard.calcFee(value);
	}

	private ExtractDTO persistTransactionAndCreateExtract(Double finalValue, Card card, EstablishmentDTO establishmentDTO,
			String description) {
		log.info("Class: CardServiceImpl | Method: persistTransactionAndCreateExtract");

		Card finalCard = cardRepository
				.findByCardNumberAndTypeCardAndBalanceGreaterThanEqual(card.getCardNumber(), card.getTypeCard(),
						finalValue)
				.orElseThrow(() -> new BusinessException(BusinessError.UNAUTHORIZED_CARD_TRANSACTION));

		finalCard.setBalance(finalCard.getBalance() - finalValue);

		log.info("Save transaction");
		cardRepository.save(finalCard);

		return createExtract(establishmentDTO, finalCard.getCardNumber(), finalValue, description);
	}

	private ExtractDTO createExtract(EstablishmentDTO establishmentDTO, Integer cardNumber, Double value,
			String description) {

		log.info("Class: CardServiceImpl | Method: createExtract");
		Extract newExtract = Extract.builder().establishmentNameId(establishmentDTO.getId())
				.establishmentName(establishmentDTO.getEstablishmentName()).productDescription(description)
				.dateBuy(new Date()).cardNumber(cardNumber).value(value).build();

		log.info("Class: Save extract");
		Extract extract = extractRepository.save(newExtract);
		return extractMapper.toDTO(extract);

	}

}
