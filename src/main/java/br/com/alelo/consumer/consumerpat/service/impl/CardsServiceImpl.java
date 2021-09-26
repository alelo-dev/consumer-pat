package br.com.alelo.consumer.consumerpat.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.entity.Cards;
import br.com.alelo.consumer.consumerpat.entity.enums.EstablishmentTypeEnum;
import br.com.alelo.consumer.consumerpat.respository.CardsRepository;
import br.com.alelo.consumer.consumerpat.service.CardsService;
import br.com.alelo.consumer.consumerpat.service.dto.response.CardsResponse;
import br.com.alelo.consumer.consumerpat.service.mapper.EntityMapper;

@Service
@Transactional
public class CardsServiceImpl implements CardsService{

	@Autowired
	private CardsRepository cardsRepository;
	
	@Autowired
	private EntityMapper<Cards, CardsResponse> cardsResponseMapper;
	
	@Override
	public CardsResponse setBalance(EstablishmentTypeEnum typeEnum, int cardNumber, double value) {
		
		Cards cards = null;
		
		if(typeEnum.equals(typeEnum.DRUGSTORE)) {
			 cards = cardsRepository.findByDrugstoreNumber(cardNumber);
			
			if(cards != null) {
				cards.setDrugstoreCardBalance(cards.getDrugstoreCardBalance() + value);
			}
		}else if(typeEnum.equals(typeEnum.FOOD)) {
			 cards = cardsRepository.findByFoodCardNumber(cardNumber);
			
			if(cards != null) {
				cards.setFoodCardBalance(cards.getFoodCardBalance() + value);
			}
		}else {
			cards =  cardsRepository.findByFuelCardNumber(cardNumber);
			
			if(cards != null) {
				cards.setFuelCardBalance(cards.getFuelCardBalance() + value);
			}
		}
		
		if(cards != null) {
			cardsRepository.save(cards);
		}
		return cardsResponseMapper.map(cards);
	}

}
