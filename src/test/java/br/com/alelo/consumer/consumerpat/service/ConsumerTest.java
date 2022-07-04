package br.com.alelo.consumer.consumerpat.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.repository.ConsumerRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class ConsumerTest {

    //TODO, need some corrections.

    // ConsumerRepository consumerRepository;
 
    // ConsumerService consumerService;

    // @Mock
    // Consumer consumer;

    // @BeforeEach
    // private void setUp() throws ParseException {

    //     Consumer consumer = new Consumer();
    //     consumer.setBirthDate(new Date());
    //     consumer.setCity("Maringá");
    //     consumer.setCountry("Brasil");
    //     consumer.setDocumentNumber(123456789);
    //     consumer.setDrugstoreCardBalance(1000);
    //     consumer.setDrugstoreNumber(1);
    //     consumer.setEmail("teste@teste.teste");
    //     consumer.setFoodCardBalance(1000);
    //     consumer.setFoodCardNumber(123456789);
    //     consumer.setFuelCardBalance(1);
    //     consumer.setFuelCardNumber(1);
    //     consumer.setMobilePhoneNumber(123456789);
    //     consumer.setName("Teste");
    //     consumer.setNumber(1);
    //     consumer.setPhoneNumber(123456789);
    //     consumer.setPortalCode(12345678);
    //     consumer.setResidencePhoneNumber(123456789);
    //     consumer.setStreet("Rua teste do teste, bairro teste numero teste");

    //     consumerRepository.save(consumer);

    //     MockitoAnnotations.openMocks(this);
    // }

    // @Test
    // public void listAllConsumers() throws ParseException{
    //     List<Consumer> consumers = consumerRepository.getAllConsumersList();
    //     List<Consumer> expected = new ArrayList<Consumer>();
    //     expected.add(consumer);
    //     assertEquals(consumers, expected);
    // }

    // @Test
    // public void createConsumer() throws ParseException{
    //     assertEquals("Consumidor criado com sucesso", consumerService.saveConsumer(consumer));
    // }

    // @Test
    // public void updateConsumer() throws ParseException{
    //     assertEquals("Nao eh permitido alterar o valor disponivel do saldo do cartao", consumerService.saveConsumer(consumer));
    // }


}
