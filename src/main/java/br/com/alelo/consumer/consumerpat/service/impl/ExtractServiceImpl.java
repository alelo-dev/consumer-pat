package br.com.alelo.consumer.consumerpat.service.impl;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.entity.Cards;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.entity.enums.EstablishmentTypeEnum;
import br.com.alelo.consumer.consumerpat.respository.CardsRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.ExtractService;
import br.com.alelo.consumer.consumerpat.service.dto.request.ExtractRequest;
import br.com.alelo.consumer.consumerpat.service.dto.response.ExtractResponse;
import br.com.alelo.consumer.consumerpat.service.mapper.EntityMapper;

@Service
@Transactional
public class ExtractServiceImpl implements ExtractService {

	@Autowired
	private ExtractRepository extractRepository;

	@Autowired
	private EntityMapper<ExtractRequest, Extract> extractRequestMapper;

	@Autowired
	private EntityMapper<Extract, ExtractResponse> extractResponseMapper;

	@Autowired
	private CardsRepository cardsRepository;

	@Override
	public ExtractResponse buy(ExtractRequest request) {

		if (request.getEstablishmentType().equals(EstablishmentTypeEnum.DRUGSTORE)) {
			buyByDrugstoreCard(request);
		} else if (request.getEstablishmentType().equals(EstablishmentTypeEnum.FOOD)) {
			buyByFoodCard(request);
		} else {
			buyByFuelCard(request);
		}
		Extract extract = extractRequestMapper.map(request);
		extract.setDateBuy(new Date());
		extract = extractRepository.save(extract);
		return extractResponseMapper.map(extract);
	}

	private void buyByFoodCard(ExtractRequest request) {
		Cards cards = null;
		// Para compras no cartão de alimentação o cliente recebe um desconto de 10%
		cards = cardsRepository.findByFoodCardNumber(request.getCardNumber());
		if (cards != null) {

			if (cards.getFoodCardBalance() > request.getValue()) {
				double cashback = (request.getValue() / 100) * 10;
				double value = request.getValue() - cashback;
				cards.setFoodCardBalance(cards.getFoodCardBalance() - value);
				cardsRepository.save(cards);
			} else {
				throw new RuntimeException("insufficient funds");
			}
		} else {
			throw new RuntimeException("card not found");
		}
	}

	private void buyByDrugstoreCard(ExtractRequest request) {
		Cards cards = null;
		cards = cardsRepository.findByDrugstoreNumber(request.getCardNumber());
		if (cards != null) {
			if (cards.getDrugstoreCardBalance() > request.getValue()) {
				cards.setDrugstoreCardBalance(cards.getDrugstoreCardBalance() - request.getValue());
				cardsRepository.save(cards);
			} else {
				throw new RuntimeException("insufficient funds");
			}
		} else {
			throw new RuntimeException("card not found");
		}
	}

	private void buyByFuelCard(ExtractRequest request) {
		Cards cards = null;
		// Nas compras com o cartão de combustivel existe um acrescimo de 35%;
		cards = cardsRepository.findByFuelCardNumber(request.getCardNumber());

		if (cards != null) {
			double tax = (request.getValue() / 100) * 35;
			double value = request.getValue() + tax;
			if (cards.getFuelCardBalance() > value) {
				cards.setFuelCardBalance(cards.getFuelCardBalance() - value);
				cardsRepository.save(cards);
			} else {
				throw new RuntimeException("insufficient funds");
			}
		} else {
			throw new RuntimeException("card not found");
		}
	}
}
