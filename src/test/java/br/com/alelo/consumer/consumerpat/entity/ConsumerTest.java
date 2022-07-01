package br.com.alelo.consumer.consumerpat.entity;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class ConsumerTest {
	Consumer consumer = new Consumer();
	Consumer consume2 = new Consumer();
	
	@Test
	public void setConsumer() {
		consumer.setBirthDate(new Date());
		consumer.setCity("SP");
		consumer.setCountry("Brasil");
		consumer.setDocumentNumber(12);
		consumer.setDrugstoreCardBalance(1.0);
		consumer.setDrugstoreNumber(1);
		consumer.setEmail("teste@test");
		consumer.setFoodCardBalance(1.0);
		consumer.setFoodCardNumber(2);
		consumer.setFuelCardBalance(1.0);
		consumer.setFuelCardNumber(3);
		consumer.setMobilePhoneNumber(12);
		consumer.setName("Test");
		consumer.setNumber(1);
		consumer.setPhoneNumber(12);
		consumer.setPortalCode(12);
		consumer.setResidencePhoneNumber(12);
		consumer.setStreet("Rua test");
	}

	
	@Test
	public void getConsumer() {
		consumer.getBirthDate();
		consumer.getCity();
		consumer.getCountry();
		consumer.getDocumentNumber();
		consumer.getDrugstoreCardBalance();
		consumer.getDrugstoreNumber();
		consumer.getEmail();
		consumer.getFoodCardBalance();
		consumer.getFoodCardNumber();
		consumer.getFuelCardBalance();
		consumer.getFuelCardNumber();
		consumer.getId();
		consumer.getMobilePhoneNumber();
		consumer.getName();
		consumer.getNumber();
		consumer.getPhoneNumber();
		consumer.getPortalCode();
		consumer.getResidencePhoneNumber();
		consumer.getStreet();
		
	}
	
	@Test
	public void equals() {
		consumer.equals(consumer);
		consume2.equals(consumer);
	}

}
