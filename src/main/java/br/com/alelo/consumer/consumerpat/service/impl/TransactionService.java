package br.com.alelo.consumer.consumerpat.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.dto.BuyDto;
import br.com.alelo.consumer.consumerpat.dto.ExtractDto;
import br.com.alelo.consumer.consumerpat.dto.RequestExtractDto;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.EstablishmentRepository;
import br.com.alelo.consumer.consumerpat.service.ITransactionService;
import br.com.alelo.consumer.consumerpat.validations.ValidationsCard;

@Service
public class TransactionService implements ITransactionService {

	@Autowired
	CardRepository cardRepository;

	@Autowired
	EstablishmentRepository establishmentRepository;
	
	
	@Autowired
	CardService cardService;

	@Autowired
	ExtractService extractService;


	private static final Integer FOOD = 1;
	private static final Integer FUEL = 2;
	private static final Integer DRUGSTORE = 2;

	@Override
	public ExtractDto buy(BuyDto buyDto) throws Exception {
		Card card = ValidationsCard.validationBuyCard(buyDto, cardRepository, establishmentRepository);
		if (card.getTypeCard().getIdTypeCard().equals(FOOD)) {
			BuyFoodRule(buyDto, card);
		} else if (card.getTypeCard().getIdTypeCard().equals(FUEL)) {
			BuyFuelRule(buyDto, card);
		} else if (card.getTypeCard().getIdTypeCard().equals(DRUGSTORE)) {
			BuyDrugstoreRule(buyDto, card);
		}
		return extractService.saveExtract(buyDto);
	}

	@Override
	public void BuyFoodRule(BuyDto buyDto, Card card) {
		BigDecimal cashback = buyDto.getValue().divide(BigDecimal.valueOf(100L)).multiply(BigDecimal.TEN);
		buyDto.setValue(buyDto.getValue().subtract(cashback));
		BuyDrugstoreRule(buyDto, card);
	}

	@Override
	public void BuyFuelRule(BuyDto buyDto, Card card) {
		BigDecimal tax = buyDto.getValue().divide(BigDecimal.valueOf(100L)).multiply(BigDecimal.valueOf(35L));
		buyDto.setValue(buyDto.getValue().add(tax));
		BuyDrugstoreRule(buyDto, card);
	}

	@Override
	public void BuyDrugstoreRule(BuyDto buyDto, Card card) {
		cardService.debitBalance(card, buyDto.getValue());
	}

	@Override
	public List<ExtractDto> consultExtract(RequestExtractDto requestExtractDto) {
		return extractService.extractAllData(requestExtractDto);
	}

}
