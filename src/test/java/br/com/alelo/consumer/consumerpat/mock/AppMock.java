package br.com.alelo.consumer.consumerpat.mock;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 25/07/2021 | 13:16
 */

import br.com.alelo.consumer.consumerpat.entity.AuthToken;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.ConsumerAddress;
import br.com.alelo.consumer.consumerpat.entity.ConsumerAleloCard;
import br.com.alelo.consumer.consumerpat.entity.ConsumerContact;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.model.dto.ConsumerAleloCardDTO;
import br.com.alelo.consumer.consumerpat.model.dto.consumer.ConsumerAddressDTO;
import br.com.alelo.consumer.consumerpat.model.dto.consumer.ConsumerContactDTO;
import br.com.alelo.consumer.consumerpat.model.dto.consumer.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.model.enums.AleloCardTypeEnum;
import br.com.alelo.consumer.consumerpat.model.enums.ContactTypeEnum;
import br.com.alelo.consumer.consumerpat.model.enums.CountryEnum;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AppMock {

    public static Extract mockExtract(){
        return new Extract(1, 1, "Padaria", "Pão", LocalDateTime.now(), "123", new BigDecimal(18.3));

    }

    public static List<Consumer> mockConsumers(){
        List<Consumer> consumers = new ArrayList<>();
        consumers.add(new Consumer(1, "Usuario A", "123456", LocalDate.of(1990,1,1), LocalDateTime.now()));
        consumers.add(new Consumer(2, "Usuario B", "741852", LocalDate.of(1990,2,1), LocalDateTime.now()));
        consumers.add(new Consumer(3, "Usuario C", "987654", LocalDate.of(1990,3,1), LocalDateTime.now()));
        consumers.add(new Consumer(4, "Usuario D", "258741", LocalDate.of(1990,4,1), LocalDateTime.now()));
        consumers.add(new Consumer(5, "Usuario E", "963741", LocalDate.of(1990,5,1), LocalDateTime.now()));
        return consumers;
    }

    public static AuthToken mockAuthToken(){
        AuthToken authToken = new AuthToken();
        authToken.setToken("7725b5b27c1d48f69a86d3920b786fdd");
        authToken.setCreatedAt(LocalDateTime.of(2021,07,01,12,00,00));
        authToken.setId(1);
        return authToken;
    }

    public static ConsumerAleloCard cardMock(){
        return new ConsumerAleloCard(1, AleloCardTypeEnum.FOOD, "123", new BigDecimal("830.56"), LocalDateTime.now(), new Consumer());
    }

    public static List<ConsumerAleloCard> cardsMock(){
        List<ConsumerAleloCard> cards = new ArrayList<>();
        cards.add(new ConsumerAleloCard(1, AleloCardTypeEnum.FOOD, "123", new BigDecimal("830.56"), LocalDateTime.now(), new Consumer()));
        cards.add(new ConsumerAleloCard(2, AleloCardTypeEnum.DRUG, "456", new BigDecimal("100.56"), LocalDateTime.now(), new Consumer()));
        cards.add(new ConsumerAleloCard(3, AleloCardTypeEnum.FUEL, "789", new BigDecimal("200.56"), LocalDateTime.now(), new Consumer()));
        return cards;
    }

    public static Consumer consumerMock(){
        return new Consumer(1, "Consumer A", "123", LocalDate.now(), LocalDateTime.now());
    }

    public static ConsumerDTO consumerDTOMock(){
        ConsumerDTO consumerDTO = new ConsumerDTO();
        List<ConsumerAleloCardDTO> cards = new ArrayList<>();
        cards.add(new ConsumerAleloCardDTO(1, AleloCardTypeEnum.FOOD, "123", new BigDecimal("830.56")));
        cards.add(new ConsumerAleloCardDTO(2, AleloCardTypeEnum.DRUG, "123", new BigDecimal("100.56")));
        cards.add(new ConsumerAleloCardDTO(3, AleloCardTypeEnum.FUEL, "123", new BigDecimal("200.56")));
        consumerDTO.setCards(cards);
        List<ConsumerAddressDTO> addresses = new ArrayList<>();
        addresses.add(new ConsumerAddressDTO(1, "Av Principal", "AB-100", "São Paulo", CountryEnum.BRAZIL, 15000000, LocalDateTime.now()));
        consumerDTO.setAddress(addresses);
        List<ConsumerContactDTO> contacts = new ArrayList<>();
        contacts.add(new ConsumerContactDTO(1, ContactTypeEnum.EMAIL, "email@email.com", LocalDateTime.now()));
        contacts.add(new ConsumerContactDTO(2, ContactTypeEnum.HOME_PHONE, "9911112222", LocalDateTime.now()));
        contacts.add(new ConsumerContactDTO(3, ContactTypeEnum.MOBILE_PHONE, "9922223333", LocalDateTime.now()));
        contacts.add(new ConsumerContactDTO(4, ContactTypeEnum.RESIDENCIAL_PHONE, "9933334444", LocalDateTime.now()));
        consumerDTO.setContacts(contacts);
        consumerDTO.setId(1);
        consumerDTO.setName("Consumer A");
        consumerDTO.setDocumentNumber("123");
        consumerDTO.setBirthDate(LocalDate.of(1990,1,1));
        return consumerDTO;
    }

    public static ConsumerContact contactMock(){
        return new ConsumerContact(1, ContactTypeEnum.EMAIL, "email@email.com", LocalDateTime.now());
    }

    public static List<ConsumerContact> listContactsMock(){
        List<ConsumerContact> contacts = new ArrayList<>();
        contacts.add(new ConsumerContact(1, ContactTypeEnum.EMAIL, "email@email.com", LocalDateTime.now()));
        contacts.add(new ConsumerContact(2, ContactTypeEnum.HOME_PHONE, "9911112222", LocalDateTime.now()));
        contacts.add(new ConsumerContact(3, ContactTypeEnum.MOBILE_PHONE, "9922223333", LocalDateTime.now()));
        contacts.add(new ConsumerContact(4, ContactTypeEnum.RESIDENCIAL_PHONE, "9933334444", LocalDateTime.now()));
        return contacts;
    }

    public static ConsumerAddress addressMock(){
        return new ConsumerAddress(1, "Av Principal", "AB-100", "São Paulo", CountryEnum.BRAZIL, 15000000, LocalDateTime.now(), new Consumer());
    }

    public static List<ConsumerAddress> listAddressMock(){
        List<ConsumerAddress> list = new ArrayList<>();
        list.add(new ConsumerAddress(1, "Av Principal", "AB-100", "São Paulo", CountryEnum.BRAZIL, 15000000, LocalDateTime.now(), new Consumer()));
        return list;
    }

}
