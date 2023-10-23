package br.com.alelo.consumer.consumerpat;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.alelo.consumer.consumerpat.controller.ConsumerController;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.exception.BusinessException;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;


/**
 * Teste para validar o fluxo completo da remessa
 * @author julio.silva
 */
@SpringBootTest
class ConsumerTestApplicationTests {

	@Autowired
	ConsumerController controller;
	
	@Autowired
	ConsumerService service;
	
	/**
	 * Testa a conexão com o banco 
	 * Testa se a base H2 subiu com os clientes
	 * Testa se existe cliente
	 */
	@Test
    public void waitingCompleteList() {		
	    List<Consumer> resultado = controller.listAllConsumers();		
	    assertFalse(resultado.isEmpty());
	}
	
	/**
	 * Testa regra de negocio saldo do cartao nao pode ser alterado
	 */
	@Test
    public void waitingBusinessException() {	
		 Exception exception = assertThrows(BusinessException.class, () -> {
			 service.updateConsumer(createMock());
		    });
		    String testMessage = "O saldo do cartão de combustível não deve ser alterado!";
		    String message = exception.getMessage();
		    assertTrue(message.contains(testMessage));
	}

	private Consumer createMock() {
		Consumer consumer = new Consumer();
		consumer.id = 1;
		consumer.name = "Julio";
		consumer.documentNumber = 9;
		consumer.birthDate = new Date();
		
		//contacts
		consumer.mobilePhoneNumber = 1111111111;
		consumer.residencePhoneNumber = 11111111;
		consumer.phoneNumber = 111111111;
		consumer.email = "juliocps.br@gmail.com";
		
		 //Address
		consumer.street = "Rua";
		consumer.number = 9;
		consumer.city = "Belo Horizonte";
		consumer.country = "Brasil";
		consumer.portalCode = 31500000;
		

	    //cards
		consumer.foodCardNumber = 987654321;
		consumer.foodCardBalance = 100000000;
		
	    consumer.fuelCardNumber = 987465123;
		consumer.fuelCardBalance = 100000001;
		
	    consumer.drugstoreNumber = 987123654;
		consumer.drugstoreCardBalance = 100000002;
		return consumer;
	}
}
