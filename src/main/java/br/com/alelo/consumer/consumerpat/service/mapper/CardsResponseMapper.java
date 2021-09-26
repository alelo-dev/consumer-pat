package br.com.alelo.consumer.consumerpat.service.mapper;

import org.springframework.stereotype.Component;

import br.com.alelo.consumer.consumerpat.entity.Cards;
import br.com.alelo.consumer.consumerpat.service.dto.response.CardsResponse;

@Component
public class CardsResponseMapper implements EntityMapper<Cards, CardsResponse>{

	@Override
	public CardsResponse map(Cards input) {
		if(input == null ) {
			return null;
		}
		
		CardsResponse result = new CardsResponse();
		result.setId(input.getId());
		result.setDrugstoreNumber(input.getDrugstoreNumber());
		result.setDrugstoreCardBalance(input.getDrugstoreCardBalance());
		result.setFoodCardNumber(input.getFoodCardNumber());
		result.setFoodCardBalance(input.getFoodCardBalance());
		result.setFuelCardNumber(input.getFuelCardNumber());
		result.setFuelCardBalance(input.getFuelCardBalance());
		return result;
	}

}
