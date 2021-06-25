package br.com.alelo.consumer.consumerpat.controller.v1;

import br.com.alelo.consumer.consumerpat.service.ExtractService;
import br.com.alelo.consumer.consumerpat.util.TypeCardsEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ExtractControllerTest {

    private final Long CARD_NUMBER = 1111111111111111L;

    @MockBean
    private ExtractService service;

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void buy() {
        //given
        UriComponentsBuilder uri = UriComponentsBuilder.fromUriString("/extract/v1/buy");
        uri.queryParam("establishmentType", TypeCardsEnum.FUEL.getValue());
        uri.queryParam("cardNumber", 1111111111111111L);
        uri.queryParam("establishmentName", "Estabelecimento");
        uri.queryParam("productDescription", "Product");
        uri.queryParam("price", new BigDecimal(10));

        // when
        var v = restTemplate.postForEntity(uri.toUriString(), null, Void.class);

        // then
        assertEquals(v.getStatusCode(), HttpStatus.OK);
    }
}