package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Address;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Contact;
import br.com.alelo.consumer.consumerpat.entity.enums.CardType;
import br.com.alelo.consumer.consumerpat.repository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.resource.impl.ConsumerResourceImpl;
import br.com.alelo.consumer.consumerpat.services.impl.ConsumerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
class ConsumerControllerTest {

    private static final Integer INDEX                  = 0;
    private static final Integer ID                     = 1;
    private static final String MOBILE_PHONE_NUMBER     = "43984634308";
    private static final String RESIDENCE_PHONE_NUMBER  = "4333393876";
    private static final String PHONE_NUMBER            = "43984990811";
    private static final String EMAIL                   = "valdircezar312@gmail.com";
    private static final String CARD_NUMBER             = "1111-2222-3333-4444";
    private static final String STREET                  = "Street";
    private static final String NUMBER                  = "Number";
    private static final String CITY                    = "City";
    private static final String COUNTRY                 = "Country";
    private static final String POSTAL_CODE             = "PostalCode";
    private static final Consumer CONSUMER              = new Consumer();
    private static final String BIRTH_DATE              = "12/02/1993";
    private static final String NAME                    = "Valdir";
    private static final String DOCUMENT_NUMBER         = "09129161925";
    private static final Double CARD_BALANCE            = 1000.0;
    private static final CardType CARD_TYPE             = CardType.FOOD;
    private static final String OBJECT_NOT_FOUND_EXCEPTION = "Objeto Não encontrado. Tipo: " + Consumer.class.getSimpleName();

    private Consumer consumer = new Consumer();
    private Contact contact;
    private Address address;
    private Card card;

    @InjectMocks
    private ConsumerResourceImpl controller;

    @Mock
    private ConsumerServiceImpl service;

    @Mock
    private ExtractRepository extractRepository;

    @BeforeEach
    void setUp() throws ParseException {
        MockitoAnnotations.openMocks(this);
        startConsumer();
    }

    @Test
    void whenFindByIdThenReturnSuccessOperation() {
        when(service.findById(anyInt())).thenReturn(consumer);

        ResponseEntity<Consumer> response = controller.findById(ID);

        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(Consumer.class, Objects.requireNonNull(response.getBody()).getClass());
        assertEquals(ID, response.getBody().getId());
        assertEquals(PHONE_NUMBER, response.getBody().getContact().getPhoneNumber());
        assertEquals(STREET, response.getBody().getAddress().getStreet());
        assertEquals(CARD_NUMBER, response.getBody().getCards().get(INDEX).getCardNumber());
    }

    @Test
    void whenFindAllThenReturnListOfConsumers() {
        when(service.findAll()).thenReturn(List.of(consumer));

        ResponseEntity<List<Consumer>> response = controller.findAll();

        assertEquals(1, Objects.requireNonNull(response.getBody()).size());
        assertEquals(Consumer.class, response.getBody().get(0).getClass());
        assertEquals(ID, response.getBody().get(0).getId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void whenCreateThenReturnSuccess() {
        when(service.create(any())).thenReturn(consumer);

        ResponseEntity<Consumer> response = controller.create(consumer);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
    }

    @Test
    void whenUpdateThenReturnSuccessOperation() {
        when(service.update(anyInt(), any())).thenReturn(consumer);

        ResponseEntity<Consumer> response = controller.update(ID, consumer);

        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Consumer.class, Objects.requireNonNull(response.getBody()).getClass());
        assertEquals(ID, response.getBody().getId());
    }

    private void startConsumer() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        contact = new Contact(ID, MOBILE_PHONE_NUMBER, RESIDENCE_PHONE_NUMBER, PHONE_NUMBER, EMAIL, CONSUMER);
        card = new Card(ID, CARD_NUMBER, CARD_BALANCE, CARD_TYPE, CONSUMER);
        address = new Address(ID, STREET, NUMBER, CITY, COUNTRY, POSTAL_CODE, CONSUMER);
        consumer = new Consumer(ID, NAME, DOCUMENT_NUMBER, sdf.parse(BIRTH_DATE), contact, address, List.of(card));
    }
}