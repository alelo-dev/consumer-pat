package br.com.alelo.consumer.consumerpat.repository;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.repository.ConsumerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class ConsumerTest {

    @Autowired
    ConsumerRepository repository;
 
    @BeforeEach
    private void setUp() throws ParseException {

        Consumer consumer = new Consumer();
        consumer.setBirthDate(new Date());
        consumer.setCity("Maringá");
        consumer.setCountry("Brasil");
        consumer.setDocumentNumber(123456789);
        consumer.setDrugstoreCardBalance(1000);
        consumer.setDrugstoreNumber(123456789);
        consumer.setEmail("teste@teste.teste");
        consumer.setFoodCardBalance(1000);
        consumer.setFoodCardNumber(123456789);
        consumer.setFuelCardBalance(1);
        consumer.setFuelCardNumber(123456789);
        consumer.setMobilePhoneNumber(123456789);
        consumer.setName("Teste");
        consumer.setNumber(1234567891);
        consumer.setPhoneNumber(123456789);
        consumer.setPortalCode(12345678);
        consumer.setResidencePhoneNumber(123456789);
        consumer.setStreet("Rua teste do teste, bairro teste numero teste");

        repository.save(consumer);

        Consumer consumer2 = new Consumer();
        consumer2.setBirthDate(new Date());
        consumer2.setCity("Maringá");
        consumer2.setCountry("Brasil");
        consumer2.setDocumentNumber(123456789);
        consumer2.setDrugstoreCardBalance(1000);
        consumer2.setDrugstoreNumber(1);
        consumer2.setEmail("teste@teste.teste");
        consumer2.setFoodCardBalance(1000);
        consumer2.setFoodCardNumber(1);
        consumer2.setFuelCardBalance(1);
        consumer2.setFuelCardNumber(1);
        consumer2.setMobilePhoneNumber(123456789);
        consumer2.setName("Teste");
        consumer2.setNumber(123456789);
        consumer2.setPhoneNumber(123456789);
        consumer2.setPortalCode(12345678);
        consumer2.setResidencePhoneNumber(123456789);
        consumer2.setStreet("Rua teste do teste, bairro teste numero teste");
        repository.save(consumer2);
    }

    @Test
    public void cantFindByDrugstoreNumber() throws ParseException{
        Consumer consumer = repository.findByDrugstoreNumber(000000);
        Assertions.assertThat(consumer).isNull();
    }

    @Test
    public void cantFindByFoodCardNumber() throws ParseException{
        Consumer consumer = repository.findByFoodCardNumber(000000);
        Assertions.assertThat(consumer).isNull();
    }
    
    @Test
    public void cantFindByFuelCardNumber() throws ParseException{
        Consumer consumer = repository.findByFuelCardNumber(000000);
        Assertions.assertThat(consumer).isNull();
    }

    @Test
    public void FindByDrugstoreNumber() throws ParseException{
        Consumer consumer = repository.findByDrugstoreNumber(123456789);
        Assertions.assertThat(consumer).isNotNull();
    }

    @Test
    public void FindByFoodCardNumber() throws ParseException{
        Consumer consumer = repository.findByFoodCardNumber(123456789);
        Assertions.assertThat(consumer).isNotNull();
    }
    
    @Test
    public void FindByFuelCardNumber() throws ParseException{
        Consumer consumer = repository.findByFuelCardNumber(123456789);
        Assertions.assertThat(consumer).isNotNull();
    }

    @Test
    public void getList() throws ParseException{
        List<Consumer> consumers = repository.getAllConsumersList();
        Assertions.assertThat(consumers).isNotNull();
    }

}
