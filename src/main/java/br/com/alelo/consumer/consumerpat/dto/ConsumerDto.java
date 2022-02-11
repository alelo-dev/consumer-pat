package br.com.alelo.consumer.consumerpat.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.alelo.consumer.consumerpat.entity.Address;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import lombok.Data;

@Data
public class ConsumerDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
    private String name;
    private String documentNumber;
    private Date birthDate;
    private Integer mobilePhoneNumber;
    private Integer residencePhoneNumber;
    private Integer phoneNumber;
    private String email;
    private List<AddressDto> addresses;
    private List<CardDto> cards;
    
    public Consumer toConsumer() {
    	Consumer consumer = new Consumer();
    	consumer.setId(this.getId());
    	consumer.setName(this.getName());
    	consumer.setDocumentNumber(this.getDocumentNumber());
    	consumer.setBirthDate(this.getBirthDate());
    	consumer.setResidencePhoneNumber(this.getResidencePhoneNumber());
    	consumer.setPhoneNumber(this.getPhoneNumber());
    	consumer.setEmail(this.getEmail());
    	
    	if (this.getAddresses() != null && this.getAddresses().size() > 0) {
    		List<Address> addresses = new ArrayList<Address>();
    		for (AddressDto addressDto : this.getAddresses()) {
				Address address = addressDto.toAddress();
				addresses.add(address);
			}
    		consumer.setAddresses(addresses);
    	}
    	
    	if (this.getCards() != null && this.getCards().size() > 0) {
    		List<Card> cards = new ArrayList<Card>();
    		for (CardDto cardDto : this.getCards()) {
				Card card = cardDto.toCard();
				cards.add(card);
			}
    		consumer.setCards(cards);
    	}
    	
    	return consumer;
    }
    
    public static ConsumerDto fromConsumer(Consumer consumer) {
    	if (consumer != null) {
	    	ConsumerDto dto = new ConsumerDto();
	    	dto.setId(consumer.getId());
	    	dto.setName(consumer.getName());
	    	dto.setDocumentNumber(consumer.getDocumentNumber());
	    	dto.setBirthDate(consumer.getBirthDate());
	    	dto.setResidencePhoneNumber(consumer.getResidencePhoneNumber());
	    	dto.setPhoneNumber(consumer.getPhoneNumber());
	    	dto.setEmail(consumer.getEmail());
	    	
	    	if (consumer.getAddresses() != null && consumer.getAddresses().size() > 0) {
	    		List<AddressDto> addresses = new ArrayList<AddressDto>();
	    		for (Address address : consumer.getAddresses()) {
					AddressDto addressDto = AddressDto.fromAddress(address);
					addresses.add(addressDto);
				}
	    		dto.setAddresses(addresses);
	    	}
	    	
	    	if (consumer.getCards() != null && consumer.getCards().size() > 0) {
	    		List<CardDto> cards = new ArrayList<CardDto>();
	    		for (Card card : consumer.getCards()) {
					CardDto cardDto = CardDto.fromCard(card);
					cards.add(cardDto);
				}
	    		dto.setCards(cards);
	    	}
	    	
	    	return dto;
    	}
    	return null;
    }
}
