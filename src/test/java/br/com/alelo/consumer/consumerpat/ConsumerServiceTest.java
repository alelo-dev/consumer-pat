package br.com.alelo.consumer.consumerpat;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ConsumerServiceTest {

    @Autowired
    private ConsumerService consumerService;

    @Test
    void createConsumerSuccess() {
        Consumer consumer = new Consumer(1, "teste", 111, LocalDateTime.now(), null, null, null);
        consumerService.save(consumer);
        assertEquals(111,consumer.getDocumentNumber());
    }

    @Test
    void consumerUpdateSuccess(){
        Consumer consumer = new Consumer(1, "teste", 111, LocalDateTime.now(), null, null, null);
        consumerService.save(consumer);
        Consumer consumerUpdated = new Consumer(1, "testeUpdated", 111, LocalDateTime.now(), null, null, null);
        consumerService.update(1,consumerUpdated);
        assertEquals("testeUpdated",consumerUpdated.getName());
    }
}
