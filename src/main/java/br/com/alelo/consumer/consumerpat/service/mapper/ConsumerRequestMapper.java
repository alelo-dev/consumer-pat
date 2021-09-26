package br.com.alelo.consumer.consumerpat.service.mapper;

import org.springframework.stereotype.Component;

import br.com.alelo.consumer.consumerpat.entity.Address;
import br.com.alelo.consumer.consumerpat.entity.Cards;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Contact;
import br.com.alelo.consumer.consumerpat.service.dto.request.AddressRequest;
import br.com.alelo.consumer.consumerpat.service.dto.request.CardsRequest;
import br.com.alelo.consumer.consumerpat.service.dto.request.ConsumerRequest;
import br.com.alelo.consumer.consumerpat.service.dto.request.ContactRequest;

@Component
public class ConsumerRequestMapper implements EntityMapper<ConsumerRequest, Consumer>{

	@Override
	public Consumer map(ConsumerRequest input) {
		if(input == null) {
			return null;
		}
		
		Consumer result = new Consumer();
		result.setId(input.getId());
		result.setName(input.getName());
		result.setDocumentNumber(input.getDocumentNumber());
		result.setBirthDate(input.getBirthDate());
		
		if(input.getAddressRequest() != null) {
		result.setAddress(mapAddress(input.getAddressRequest()));
			
		}
		
		if(input.getCardsRequest() != null) {
		result.setCards(mapCards(input.getCardsRequest()));
		}
		
		if(input.getContactRequest() != null) {
			result.setContact(mapContact(input.getContactRequest()));
		}
		
		return result;
	}
	
	public  Address mapAddress(AddressRequest input) {
		if(input == null) {
			return null;
		}
		
		Address result = new Address();
		result.setId(input.getId());
		result.setCity(input.getCity());
		result.setCountry(input.getCountry());
		result.setNumber(input.getNumber());
		result.setPortalCode(input.getPortalCode());
		result.setStreet(input.getStreet());
		return result;
	}
	
	public Cards mapCards(CardsRequest input) {
		if(input == null) {
			return null;
		}
		
		Cards result = new Cards();
		result.setId(input.getId());
		result.setDrugstoreNumber(input.getDrugstoreNumber());
		result.setDrugstoreCardBalance(input.getDrugstoreCardBalance());
		result.setFoodCardNumber(input.getFoodCardNumber());
		result.setFoodCardBalance(input.getFoodCardBalance());
		result.setFuelCardNumber(input.getFuelCardNumber());
		result.setFuelCardBalance(input.getFuelCardBalance());
		return result;
	}
	
	public Contact mapContact(ContactRequest input) {
		if(input == null) {
			return null;
		}
		
		Contact result = new Contact();
		result.setId(input.getId());
		result.setEmail(input.getEmail());
		result.setMobilePhoneNumber(input.getMobilePhoneNumber());
		result.setPhoneNumber(input.getPhoneNumber());
		result.setResidencePhoneNumber(input.getResidencePhoneNumber());
		return result;
	}

}
