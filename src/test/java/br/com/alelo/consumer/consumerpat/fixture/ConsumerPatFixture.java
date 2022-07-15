package br.com.alelo.consumer.consumerpat.fixture;

import br.com.alelo.consumer.consumerpat.model.entity.*;
import br.com.alelo.consumer.consumerpat.model.enums.ContactType;
import br.com.alelo.consumer.consumerpat.model.enums.EstablishmentType;
import br.com.alelo.consumer.consumerpat.web.vo.consumer.NewConsumerFormVO;
import br.com.alelo.consumer.consumerpat.web.vo.consumer.UpdateConsumerFormVO;
import br.com.alelo.consumer.consumerpat.web.vo.extract.NewExtractFormVO;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ConsumerPatFixture {

    public static final Long ADDRESS_ID = 1L;
    private static final String ADDRESS_STREET = "Rua Acézio Guedes";
    private static final Integer ADDRESS_NUMBER = 482;
    private static final String ADDRESS_CITY = "Macapá";
    private static final String ADDRESS_COUNTRY = "Brasil";
    private static final Long ADDRESS_POSTAL_CODE = 68905628L;

    public static final Long CONSUMER_ID = 18L;
    public static final Long CONSUMER_INVALID_ID = 0L;
    private static final String CONSUMER_NAME = "Bernardo Osvaldo da Rosa";
    private static final String CONSUMER_DOCUMENT_NUMBER = "56705170247";
    private static final LocalDate CONSUMER_BIRTH_DATE = LocalDate.of(2003, 5, 23);

    public static final Long CARD_ID = 1L;
    public static final Long CARD_INVALID_NUMBER = 0L;
    public static final Long CARD_NUMBER = 5100921398143993L;
    private static final BigDecimal CARD_BALANCE = BigDecimal.valueOf(1500.00);
    private static final EstablishmentType CARD_TYPE = EstablishmentType.FOOD;

    private static final Long CONTACT_ID = 15L;
    private static final String CONTACT_VALUE = "consumer@alelo.com";
    private static final ContactType CONTACT_TYPE = ContactType.EMAIL;

    public static final Long EXTRACT_ID = 22L;
    public static final Long EXTRACT_INVALID_ID = 0L;
    private static final Long EXTRACT_ESTABLISHMENT_ID = 108880L;
    private static final String EXTRACT_ESTABLISHMENT_NAME = "Levi Alimentos Ltda";
    private static final String EXTRACT_PRODUCT_DESCRIPTION = "Manteiga Davaca com Sal 500g - Hiperideal";
    private static final LocalDateTime EXTRACT_DATE_BUY = LocalDateTime.now();
    private static final BigDecimal EXTRACT_VALUE = BigDecimal.valueOf(23.99);

    public ConsumerPatFixture() {}

    public static Address buildAddress() {
        return new Address(ADDRESS_ID, ADDRESS_STREET, ADDRESS_NUMBER, ADDRESS_CITY, ADDRESS_COUNTRY, ADDRESS_POSTAL_CODE);
    }

    public static Contact buildContact() {
        return new Contact(CONTACT_ID, CONTACT_VALUE, CONTACT_TYPE, null);
    }

    public static Consumer buildConsumer() {
        Consumer consumer = new Consumer();
        consumer.setId(CONSUMER_ID);
        consumer.setName(CONSUMER_NAME);
        consumer.setDocumentNumber(CONSUMER_DOCUMENT_NUMBER);
        consumer.setBirthDate(CONSUMER_BIRTH_DATE);
        return consumer;
    }

    public static NewConsumerFormVO buildNewConsumerFormVo(Consumer consumer) {
        NewConsumerFormVO body = new NewConsumerFormVO();
        body.setName(consumer.getName());
        body.setDocumentNumber(consumer.getDocumentNumber());
        body.setBirthDate(consumer.getBirthDate());

        Address address = consumer.getAddress();
        if (address != null) {
            body.setStreet(address.getStreet());
            body.setNumber(address.getNumber());
            body.setCity(address.getCity());
            body.setCountry(address.getCountry());
            body.setPostalCode(address.getPostalCode());
        }

        return body;
    }

    public static UpdateConsumerFormVO buildUpdateConsumerFormVO(Consumer consumer) {
        UpdateConsumerFormVO body = new UpdateConsumerFormVO();
        body.setName(consumer.getName());
        body.setDocumentNumber(consumer.getDocumentNumber());
        body.setBirthDate(consumer.getBirthDate());
        return body;
    }


    public static Card buildCard(EstablishmentType type, Consumer consumer) {
        return new Card(CARD_ID, CARD_NUMBER, CARD_BALANCE, type, consumer);
    }

    public static Extract buildExtract(Card card, Consumer consumer) {
        return new Extract(EXTRACT_ID, EXTRACT_ESTABLISHMENT_ID, EXTRACT_ESTABLISHMENT_NAME,
                EXTRACT_PRODUCT_DESCRIPTION, EXTRACT_DATE_BUY, EXTRACT_VALUE, card, consumer);
    }

    public static NewExtractFormVO buildExtractFormVo(Extract extract, EstablishmentType establishmentType) {
        NewExtractFormVO body = new NewExtractFormVO();
        body.setEstablishmentId(extract.getEstablishmentId());
        body.setEstablishmentName(extract.getEstablishmentName());
        body.setEstablishmentType(establishmentType.getValue());
        body.setProductDescription(extract.getProductDescription());
        body.setValue(extract.getValue());

        if (extract.getCard() != null) {
            body.setCardNumber(extract.getCard().getNumber());
        }
        return body;
    }
}
