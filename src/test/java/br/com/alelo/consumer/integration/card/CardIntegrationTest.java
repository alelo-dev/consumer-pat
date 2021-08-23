package br.com.alelo.consumer.integration.card;

import br.com.alelo.consumer.integration.consumer.ConsumerIntegrationTest;
import br.com.alelo.consumerpat.core.dataprovider.entity.CardEntity;
import br.com.alelo.consumerpat.core.dataprovider.entity.ExtractEntity;
import br.com.alelo.consumerpat.core.dto.v1.request.CardBuyV1RequestDto;
import br.com.alelo.consumerpat.core.dto.v1.request.CardRechargeV1RequestDto;
import br.com.alelo.consumerpat.core.enumeration.EstablishmentType;
import br.com.alelo.consumerpat.dataprovider.repository.CardRepository;
import br.com.alelo.consumerpat.dataprovider.repository.ExtractRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CardIntegrationTest extends ConsumerIntegrationTest {

    private final String URI = "/v1/cards/";

    @Test
    void rechargeTest() {
        this.createConsumerTest();

        CardRechargeV1RequestDto payload = CardRechargeV1RequestDto.builder()
                .value(BigDecimal.valueOf(100L))
                .build();
        String cardNumber = "1234432165479854";
        String uri = URI + cardNumber + "/recharge";

        HttpEntity<CardRechargeV1RequestDto> request = new HttpEntity<>(payload);
        ResponseEntity<String> exchange = this.testRestTemplate.exchange(this.getUrl(uri), HttpMethod.PUT, request, String.class);

        assertEquals(204, exchange.getStatusCode().value());

        CardEntity byCardNumber = cardRepository.findByCardNumber(cardNumber);

        assertEquals(1100L, byCardNumber.getBalance().longValue());
    }

    @Test
    void rechargeNotFoundTest() {
        this.createConsumerTest();

        CardRechargeV1RequestDto payload = CardRechargeV1RequestDto.builder()
                .value(BigDecimal.valueOf(100L))
                .build();
        String cardNumber = "123443165429850";
        String uri = URI + cardNumber + "/recharge";

        HttpEntity<CardRechargeV1RequestDto> request = new HttpEntity<>(payload);
        ResponseEntity<String> exchange = this.testRestTemplate.exchange(this.getUrl(uri), HttpMethod.PUT, request, String.class);

        assertEquals(404, exchange.getStatusCode().value());
    }

    @Test
    void buysTest() {
        this.createConsumerTest();

        CardBuyV1RequestDto payload = CardBuyV1RequestDto.builder()
                .value(BigDecimal.valueOf(100))
                .establishmentName("Name")
                .productDescription("Product")
                .establishmentType(EstablishmentType.FOOD)
                .build();

        String cardNumber = "1234432165479854";
        String uri = URI + cardNumber + "/buys";

        HttpEntity<CardBuyV1RequestDto> request = new HttpEntity<>(payload);
        ResponseEntity<String> exchange = this.testRestTemplate.exchange(this.getUrl(uri), HttpMethod.PUT, request, String.class);

        assertEquals(204, exchange.getStatusCode().value());

        ExtractEntity extractEntity = this.extractRepository.findAll().get(0);

        assertNotNull(extractEntity.getDateBuy());
        assertEquals("Name", extractEntity.getEstablishmentName());
        assertEquals(EstablishmentType.FOOD, extractEntity.getEstablishmentType());
        assertEquals("Product", extractEntity.getProductDescription());
        assertEquals(90L, extractEntity.getValue().longValue());
    }

    @Test
    void buysCardNotFoundTest() {
        this.createConsumerTest();

        CardBuyV1RequestDto payload = CardBuyV1RequestDto.builder()
                .value(BigDecimal.valueOf(100))
                .establishmentName("Name")
                .productDescription("Product")
                .establishmentType(EstablishmentType.FOOD)
                .build();

        String cardNumber = "43443165279854";
        String uri = URI + cardNumber + "/buys";

        HttpEntity<CardBuyV1RequestDto> request = new HttpEntity<>(payload);
        ResponseEntity<String> exchange = this.testRestTemplate.exchange(this.getUrl(uri), HttpMethod.PUT, request, String.class);

        assertEquals(404, exchange.getStatusCode().value());
    }
}
