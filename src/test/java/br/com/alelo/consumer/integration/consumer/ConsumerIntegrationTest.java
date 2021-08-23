package br.com.alelo.consumer.integration.consumer;

import br.com.alelo.consumer.ConsumerPatTestApplication;
import br.com.alelo.consumerpat.core.dataprovider.entity.ConsumerEntity;
import br.com.alelo.consumerpat.core.dto.v1.request.*;
import br.com.alelo.consumerpat.core.dto.v1.response.ConsumerV1ResponseDto;
import br.com.alelo.consumerpat.core.dto.v1.response.PaginatedResponseDto;
import br.com.alelo.consumerpat.core.enumeration.CardType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class ConsumerIntegrationTest extends ConsumerPatTestApplication {

    private final String URI = "/v1/consumers";

    @Test
    public void forbiddenTest() {
        ResponseEntity<String> exchange = this.testRestTemplate.exchange(this.getUrl(URI), HttpMethod.GET, null, String.class);

        assertEquals(403, exchange.getStatusCode().value());
    }

    @Test
    public void createConsumerTest() {
        Set<CardV1RequestDto> cards = Set.of(CardV1RequestDto.builder()
                        .card("1234432165479854")
                        .balance(BigDecimal.valueOf(1000L))
                        .type(CardType.FOOD)
                        .build(),
                CardV1RequestDto.builder()
                        .card("1234432165479856")
                        .balance(BigDecimal.valueOf(900L))
                        .type(CardType.FUEL)
                        .build(),
                CardV1RequestDto.builder()
                        .card("1234432165479855")
                        .balance(BigDecimal.valueOf(800L))
                        .type(CardType.DRUGSTORE)
                        .build());

        ConsumerCreateV1RequestDto payload = ConsumerCreateV1RequestDto.
                builder()
                .birthDate(LocalDate.of(2000, 1, 1))
                .document("95054214003")
                .name("Luis")
                .contact(ContactV1RequestDto.builder()
                        .email("email_test@email.com")
                        .mobilePhone("16997583259")
                        .residencePhone("35168476")
                        .build())
                .address(AddressV1RequestDto.builder()
                        .city("Araraquara")
                        .country("Brasil")
                        .number(10)
                        .postalCode("14800000")
                        .street("Rua A")
                        .build())
                .cards(cards)
                .build();

        HttpEntity<ConsumerCreateV1RequestDto> request = new HttpEntity<>(payload, this.getAuthorizationHeader());
        ResponseEntity<String> exchange = this.testRestTemplate.exchange(this.getUrl(URI), HttpMethod.POST, request, String.class);

        assertEquals(201, exchange.getStatusCode().value());
    }

    @Test
    void updateConsumerNotFoundTest() {
        String uri = URI + "/" + UUID.randomUUID();
        ConsumerUpdateV1RequestDto payload = ConsumerUpdateV1RequestDto.
                builder()
                .birthDate(LocalDate.of(2001, 1, 2))
                .name("Luis Gustavo")
                .contact(ContactV1RequestDto.builder()
                        .email("email_test1@email.com")
                        .mobilePhone("16997583250")
                        .residencePhone("35168477")
                        .build())
                .address(AddressV1RequestDto.builder()
                        .city("Araraquara")
                        .country("Brasil")
                        .number(11)
                        .postalCode("14800001")
                        .street("Rua B")
                        .build())
                .build();

        HttpEntity<ConsumerUpdateV1RequestDto> request = new HttpEntity<>(payload, this.getAuthorizationHeader());
        ResponseEntity<String> exchange = this.testRestTemplate.exchange(this.getUrl(uri), HttpMethod.PUT, request, String.class);

        assertEquals(404, exchange.getStatusCode().value());
    }

    @Test
    void updateConsumerTest() {
        this.createConsumerTest();

        ConsumerEntity byDocument = this.consumerRepository.findByDocument("95054214003");

        ConsumerUpdateV1RequestDto payload = ConsumerUpdateV1RequestDto.
                builder()
                .birthDate(LocalDate.of(2001, 1, 2))
                .name("Luis Gustavo")
                .contact(ContactV1RequestDto.builder()
                        .email("email_test1@email.com")
                        .mobilePhone("16997583250")
                        .residencePhone("35168477")
                        .build())
                .address(AddressV1RequestDto.builder()
                        .city("Araraquara")
                        .country("Brasil")
                        .number(11)
                        .postalCode("14800001")
                        .street("Rua B")
                        .build())
                .build();

        String uri = URI + "/" + byDocument.getConsumerCode();

        HttpEntity<ConsumerUpdateV1RequestDto> request = new HttpEntity<>(payload, this.getAuthorizationHeader());
        ResponseEntity<String> exchange = this.testRestTemplate.exchange(this.getUrl(uri), HttpMethod.PUT, request, String.class);
        byDocument = this.consumerRepository.findByDocument("95054214003");

        assertEquals(204, exchange.getStatusCode().value());
        assertEquals("Luis Gustavo", byDocument.getName());
        assertEquals("email_test1@email.com", byDocument.getContact().getEmail());
        assertEquals(LocalDate.of(2001, 1, 2), byDocument.getBirthDate());
        assertEquals("16997583250", byDocument.getContact().getMobilePhone());
        assertEquals("35168477", byDocument.getContact().getResidencePhone());
        assertEquals("Rua B", byDocument.getAddress().getStreet());
        assertEquals(11, byDocument.getAddress().getNumber());
        assertEquals("14800001", byDocument.getAddress().getPostalCode());
    }

    @Test
    void findAllConsumersTest() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.registerModule(new JavaTimeModule());

        this.createConsumerTest();

        HttpEntity<ConsumerUpdateV1RequestDto> request = new HttpEntity<>(null, this.getAuthorizationHeader());
        ResponseEntity<String> exchange = this.testRestTemplate.exchange(this.getUrl(URI), HttpMethod.GET, request, String.class);

        PaginatedResponseDto<ConsumerV1ResponseDto> dto = objectMapper.readValue(exchange.getBody(), new TypeReference<>() {
        });

        assertEquals(200, exchange.getStatusCode().value());
        assertTrue(dto.getFirstPage());
        assertTrue(dto.getLastPage());
        assertEquals(10, dto.getSize());
        assertEquals(1, dto.getTotalElements());
        assertEquals(1, dto.getTotalPages());

        ConsumerV1ResponseDto consumerV1ResponseDto = dto.getContent().get(0);

        assertNotNull(consumerV1ResponseDto.getConsumerCode());
        assertEquals(LocalDate.of(2000, 1, 1), consumerV1ResponseDto.getBirthDate());
        assertEquals("Luis", consumerV1ResponseDto.getName());
        assertEquals("email_test@email.com", consumerV1ResponseDto.getContact().getEmail());
        assertEquals("16997583259", consumerV1ResponseDto.getContact().getMobilePhone());
        assertEquals("35168476", consumerV1ResponseDto.getContact().getResidencePhone());
        assertEquals("Rua A", consumerV1ResponseDto.getAddress().getStreet());
        assertEquals(10, consumerV1ResponseDto.getAddress().getNumber());
        assertEquals("14800000", consumerV1ResponseDto.getAddress().getPostalCode());
    }
}
