package br.com.alelo.consumer.consumerpat.entity;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class ExtractTest {
	Extract extract = new Extract();
	Extract extractConstructor = new Extract(1,1,"Test","Test",new Date(),1,2.0);
	Extract extractConstructor2 = new Extract("Test",new Date(),1,2.0);
	Extract extractConstructo3 = new Extract(1,"Test","Test",new Date(),1,2.0);
	
	@Test
	public void setExtract() {
		extract.setCardNumber(1);
		extract.setDateBuy(new Date());
		extract.setEstablishmentName("Test");
		extract.setEstablishmentNameId(1);
		extract.setId(2);
		extract.setProductDescription("Test");
		extract.setValue(2.0);
		
		
		
	}
	
	@Test
	public void getExtract() {
		extract.getCardNumber();
		extract.getDateBuy();
		extract.getEstablishmentName();
		extract.getEstablishmentNameId();
		extract.getId();
		extract.getProductDescription();
		extract.getValue();
	}
}
