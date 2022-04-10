package br.com.alelo.consumer.consumerpat.utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.alelo.consumer.consumerpat.dto.AddressDto;
import br.com.alelo.consumer.consumerpat.dto.CardDto;
import br.com.alelo.consumer.consumerpat.dto.ConsumerDto;
import br.com.alelo.consumer.consumerpat.dto.ContactDto;
import br.com.alelo.consumer.consumerpat.dto.TypeCardDto;
import br.com.alelo.consumer.consumerpat.service.impl.ConsumerService;

@Configuration
public class CreateLoadInitialData implements CommandLineRunner {

	@Autowired
	ConsumerService consumerService;
	
	@Override
	public void run(String... args) throws Exception {
		
        for (int i = 0; i < 500; i++) {
        	
        	ConsumerDto consumerDto = new ConsumerDto();
        	consumerDto.setName("user main create "+i);
        	consumerDto.setBirthDate(date());
        	consumerDto.setDocumentNumber(generateRandomIntIntRange(10000000, 99999999));
        	
        	List<ContactDto> listContactDto = new ArrayList<ContactDto>();
        	listContactDto.add(createFakeContactDto(i));
        	listContactDto.add(createFakeContactDto(i*1000));
        	consumerDto.setContacts(listContactDto);
        	
        	List<CardDto> listCardDto = new ArrayList<CardDto>();
        	
        	TypeCardDto food = new TypeCardDto();
        	food.setIdTypeCard(1L);
        	listCardDto.add(createFakeContactDto(food));
        	
        	TypeCardDto fuel = new TypeCardDto();
        	fuel.setIdTypeCard(2L);
        	listCardDto.add(createFakeContactDto(fuel));
        	
        	TypeCardDto drugstore = new TypeCardDto();
        	drugstore.setIdTypeCard(3L);
        	listCardDto.add(createFakeContactDto(drugstore));
        	
        	consumerDto.setCards(listCardDto);
  
        	List<AddressDto> listAddressDto = new ArrayList<AddressDto>();
        	listAddressDto.add(createFakeAdressDto(i));
        	listAddressDto.add(createFakeAdressDto(i*2000));
         	listAddressDto.add(createFakeAdressDto(i*4000));
         	listAddressDto.add(createFakeAdressDto(i*6000));
         	consumerDto.setAddresses(listAddressDto);
       
        	consumerService.createConsumer(consumerDto);
		}
		
	}
	
 
	private ContactDto createFakeContactDto(Integer i) {
		ContactDto contactDto = new ContactDto();
		contactDto.setEmail("email"+i+"@email.com");
		contactDto.setMobilePhoneNumber(generateRandomIntIntRange(10000000, 99999999));
		contactDto.setPhoneNumber(generateRandomIntIntRange(10000000, 99999999));
		contactDto.setResidencePhoneNumber(generateRandomIntIntRange(10000000, 99999999));
		return contactDto;
	}
	
	private CardDto createFakeContactDto(TypeCardDto typeCard) {
		CardDto cardDto = new CardDto();
		cardDto.setCardNumber(Long.valueOf(generateRandomIntIntRange(100000, 9999999)));
		cardDto.setTypeCard(typeCard);
		return cardDto;
	}
	
	
	private AddressDto createFakeAdressDto(Integer i) {
		AddressDto addressDto = new AddressDto();
		addressDto.setCity("city number"+i);
		addressDto.setCountry("cc"+i);
		addressDto.setNumber(i);
		addressDto.setPortalCode(generateRandomIntIntRange(10000, 99999));
		addressDto.setStreet("street number"+i);
		return addressDto;
	}
    
    public static LocalDate date() {
        int hundredYears = 100 * 365;
        return LocalDate.ofEpochDay(ThreadLocalRandom
          .current().nextInt(-hundredYears, hundredYears));
    }
    
    public static String generateRandomIntIntRange(int min, int max) {
        Random r = new Random();
        Integer value = r.nextInt((max - min) + 1) + min;
        return value.toString();
        
        
    }



}
