package br.com.alelo.consumer.consumerpat;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.alelo.consumer.consumerpat.controller.ExtractController;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;

@SpringBootTest
public class ExtractUnitTest {
    @Autowired
    ExtractRepository extractRepository;
    @Autowired
	ConsumerRepository consumerRepository;
	@Autowired
	ExtractController extractController;

	@BeforeEach
	private void setup(){
		consumerRepository.deleteAll();
		extractRepository.deleteAll();
	}

	Consumer consumer1 = new Consumer("name", 1, new Date(), 12345678, 123456789, 123456789, "email@email.com", "street", 1, "city", "country", 12345678, 123, 1000, 123, 1000, 123, 1000);

    @Test
    public void buySuccessfully1(){
        consumerRepository.save(consumer1);

        extractController.buy(1, "establishmentName", consumerRepository.findById(consumer1.getId()).orElse(null).getFoodCardNumber(), "productDescription", 10);

        assertNotNull(extractRepository.findById(1));
    }
}
