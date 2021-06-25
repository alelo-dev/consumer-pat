package br.com.alelo.consumer.consumerpat.controller.v1;

import br.com.alelo.consumer.consumerpat.data.vo.v1.ConsumerVO;
import br.com.alelo.consumer.consumerpat.mocks.MockConsumer;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import br.com.alelo.consumer.consumerpat.util.TypeCardsEnum;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ConsumerControllerTest {

    MockConsumer inputObject;

    @MockBean
    private ConsumerService service;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    void setUp() { inputObject = new MockConsumer(); }

    @Test
    void listAllConsumers() {
        // when
        var v = restTemplate.getForEntity("/consumer/v1/consumerList", Page.class);

        // then
        assertEquals(v.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void createConsumer() {
        // when
        var v = restTemplate.postForEntity("/consumer/v1/createConsumer", inputObject.mockVO(), ConsumerVO.class);

        // then
        assertEquals(v.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    void updateConsumer() {
        // when
        var v = restTemplate.postForEntity("/consumer/v1/updateConsumer",
                inputObject.mockVO(), ConsumerVO.class);

        // then
        assertEquals(v.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void setBalance() {
        //given
        UriComponentsBuilder uri = UriComponentsBuilder.fromUriString("/consumer/v1/setcardbalance");
        uri.queryParam("cardNumber", 1111111111111111L);
        uri.queryParam("amount", new BigDecimal(100));

        // when
        var v = restTemplate.postForEntity(uri.toUriString(), null, Void.class);

        // then
        assertEquals(v.getStatusCode(), HttpStatus.OK);
    }
}