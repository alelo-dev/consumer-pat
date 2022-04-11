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
import br.com.alelo.consumer.consumerpat.entity.Establishment;
import br.com.alelo.consumer.consumerpat.entity.TypeEstablishment;
import br.com.alelo.consumer.consumerpat.respository.EstablishmentRepository;
import br.com.alelo.consumer.consumerpat.respository.TypeEstablishmentRepository;
import br.com.alelo.consumer.consumerpat.service.impl.ConsumerService;

@Configuration
public class CreateLoadInitialData implements CommandLineRunner {

	@Autowired
	ConsumerService consumerService;
	
	@Autowired
	EstablishmentRepository establishmentRepository;
	
	@Autowired
	TypeEstablishmentRepository typeEstablishmentRepository;
	
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
        	food.setIdTypeCard(1);
        	TypeCardDto fuel = new TypeCardDto();
        	fuel.setIdTypeCard(2);
        	TypeCardDto drugstore = new TypeCardDto();
        	drugstore.setIdTypeCard(3);
        	listCardDto.add(createFakeContactDto(food));
            listCardDto.add(createFakeContactDto(fuel));
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
        
      
        Establishment establishmentWallmart = new Establishment();
        establishmentWallmart.setNameEstablishment("Wallmart");
        Establishment save1 = establishmentRepository.save(establishmentWallmart);   
        
        TypeEstablishment food = new TypeEstablishment();
    	food.setIdTypeEstablishment(1);
    	food.setTypeEstablishment("Food");
    	food = typeEstablishmentRepository.save(food);
    	
        List<TypeEstablishment> typeCardsAcceptedWallmart = new ArrayList<TypeEstablishment>();
        typeCardsAcceptedWallmart.add(food);
        establishmentWallmart.setTypeEstablishments(typeCardsAcceptedWallmart);
        establishmentRepository.save(save1);
        
        
        Establishment establishmentShell = new Establishment();
        establishmentShell.setNameEstablishment("Shell");
        Establishment save2 = establishmentRepository.save(establishmentShell);   
        
        TypeEstablishment fuel = new TypeEstablishment();
        fuel.setIdTypeEstablishment(2);
        fuel.setTypeEstablishment("Fuel");
        fuel = typeEstablishmentRepository.save(fuel);
    	
        List<TypeEstablishment> typeCardsAcceptedShell = new ArrayList<TypeEstablishment>();
        typeCardsAcceptedShell.add(fuel);
        establishmentShell.setTypeEstablishments(typeCardsAcceptedShell);
        establishmentRepository.save(save2);
    	
    	
        Establishment establishmentWalgreens = new Establishment();
        establishmentWalgreens.setNameEstablishment("Walgreens");
        Establishment save3 = establishmentRepository.save(establishmentWalgreens);   
        
        TypeEstablishment drugstore = new TypeEstablishment();
        drugstore.setIdTypeEstablishment(3);
        drugstore.setTypeEstablishment("Drugstore");
        drugstore = typeEstablishmentRepository.save(drugstore);
    	
        List<TypeEstablishment> typeCardsAcceptedWalgreens = new ArrayList<TypeEstablishment>();
        typeCardsAcceptedWalgreens.add(drugstore);
        establishmentWalgreens.setTypeEstablishments(typeCardsAcceptedWalgreens);
        establishmentRepository.save(save3);
 
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
