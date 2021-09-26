package br.com.alelo.consumer.consumerpat.service.mapper;

import org.springframework.stereotype.Component;

import br.com.alelo.consumer.consumerpat.entity.Address;
import br.com.alelo.consumer.consumerpat.entity.Cards;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Contact;
import br.com.alelo.consumer.consumerpat.service.dto.response.AddressResponse;
import br.com.alelo.consumer.consumerpat.service.dto.response.CardsResponse;
import br.com.alelo.consumer.consumerpat.service.dto.response.ConsumerResponse;
import br.com.alelo.consumer.consumerpat.service.dto.response.ContactResponse;

@Component
public class ConsumerResponseMapper implements EntityMapper<Consumer, ConsumerResponse>{

	@Override
	public ConsumerResponse map(Consumer input) {

		if(input == null) {
			return null;
		}
		
		ConsumerResponse result = new ConsumerResponse();
		result.setId(input.getId());
		result.setName(input.getName());
		result.setDocumentNumber(input.getDocumentNumber());
		result.setBirthDate(input.getBirthDate());
		
		if(input.getAddress() != null) {
		result.setAddressResponse(mapAddress(input.getAddress()));
		}
		
		if(input.getCards() != null) {
		result.setCardsResponse(mapCards(input.getCards()));
		}
		
		if(input.getContact() != null) {
		result.setContactResponse(mapContact(input.getContact()));
		}
		
		return result;
	}
	
	public  AddressResponse mapAddress(Address input) {
		if(input == null) {
			return null;
		}
		
		AddressResponse result = new AddressResponse();
		result.setId(input.getId());
		result.setCity(input.getCity());
		result.setCountry(input.getCountry());
		result.setNumber(input.getNumber());
		result.setPortalCode(input.getPortalCode());
		result.setStreet(input.getStreet());
		return result;
	}
	
	public CardsResponse mapCards(Cards input) {
		if(input == null) {
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
	
	public ContactResponse mapContact(Contact input) {
		if(input == null) {
			return null;
		}
		
		ContactResponse result = new ContactResponse();
		result.setId(input.getId());
		result.setEmail(input.getEmail());
		result.setMobilePhoneNumber(input.getMobilePhoneNumber());
		result.setPhoneNumber(input.getPhoneNumber());
		result.setResidencePhoneNumber(input.getResidencePhoneNumber());
		return result;
	}


}
