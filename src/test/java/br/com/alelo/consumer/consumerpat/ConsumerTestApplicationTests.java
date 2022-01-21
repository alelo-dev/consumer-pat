package br.com.alelo.consumer.consumerpat;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.alelo.consumer.consumerpat.service.ConsumerService;

@SpringBootTest
class ConsumerTestApplicationTests {

    @Mock
    private ConsumerService consumerService;

    @Test
    void createConsumer() {
        when(consumerService.registerConsumer(ConsumerMock.getConsumer())).thenReturn(Boolean.TRUE);
    }
    
}