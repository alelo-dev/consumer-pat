package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.model.Consumer;
import br.com.alelo.consumer.consumerpat.model.Contact;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.time.LocalDate;

class ConsumerServiceTest {

    @Test
    void findAllPageable() {
    }

    @Test
    void create() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Consumer consumer = new Consumer();
        consumer.setName("Doc");
        consumer.setBirthDate(LocalDate.of(2000, 10, 30));
        consumer.setDocumentNumber("333444555-X");
        //consumer.addCard(getCardDrugStore());
        consumer.setContact(getContact());
        System.out.println(mapper.writeValueAsString(consumer));
        BigInteger bi = new BigInteger("9999888877776666");
        System.out.println(bi);
    }

/*
    private Card getCardDrugStore() {
        Card card = new Card();
        card.setBalance(BigDecimal.valueOf(900));
        card.setNumber(new BigInteger("9999888877776666"));
        card.setType(CardType.Drugstore);
        return card;
    }
*/

    private Contact getContact() {
        Contact contact = new Contact();
        contact.setCity("Sao Paulo");
        contact.setCountry("Brazil");
        contact.setMobileNumber("+55 11 98374-2978");
        return contact;
    }

    @Test
    void update() {
    }
}