package br.com.alelo.consumer.consumerpat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alelo.consumer.consumerpat.model.EstablishementCards;
import br.com.alelo.consumer.consumerpat.model.FuelCards;
import br.com.alelo.consumer.consumerpat.model.dto.BuyDTO;
import br.com.alelo.consumer.consumerpat.model.entity.Cards;
import br.com.alelo.consumer.consumerpat.respository.CardsRepository;

@Service
public class CardsServiceImp implements CardsService {

	@Autowired
	CardsRepository repository;

	@Override
	public void buy(EstablishementCards cards, BuyDTO dto) {
		// TODO Auto-generated method stub
		cards.cashback(dto.getValue());
		System.out.println(cards.getCardBalance());

		saveCards(cards);

	}

	@Override
	public void buy(FuelCards cards, BuyDTO dto) {
		// TODO Auto-generated method stub
		cards.tax(dto.getValue());
		saveCards(cards);
	}

	@Override
	public void buy(Cards cards, BuyDTO dto) {
		// TODO Auto-generated method stub
		saveCards(cards);
	}

	@Transactional
	private void saveCards(Cards cards) {

				Cards cardsS = new Cards(cards.getId(), cards.getConsumer(), cards.getCardNumber(), cards.getCardBalance(),
					cards.getCardsType());
			repository.save(cardsS);
		

	}

}
