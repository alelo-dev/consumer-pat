package br.com.alelo.consumer.consumerpat.domain.service;

import br.com.alelo.consumer.consumerpat.domain.entity.Consumer;
import br.com.alelo.consumer.consumerpat.domain.service.exception.ApiException;
import br.com.alelo.consumer.consumerpat.domain.service.exception.Code;
import br.com.alelo.consumer.consumerpat.helper.ConsumerHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ConsumerServiceTest {

    @Autowired
    private ConsumerService consumerService;

    @Test
    void shouldSave() throws ApiException {
        Consumer consumer = consumerService.save(ConsumerHelper.buildConsumer());
        Assertions.assertNotNull(consumer);
    }

    @Test
    void shouldUpdate() throws ApiException {
        Consumer consumer = consumerService.save(ConsumerHelper.buildConsumer());
        consumer.setName("Jose Ferreira");
        Consumer consumerUpdated = consumerService.update(consumer);
        Assertions.assertEquals(consumer.getName(), consumerUpdated.getName());
    }


}
