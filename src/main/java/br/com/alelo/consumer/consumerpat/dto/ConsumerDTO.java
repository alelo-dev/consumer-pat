package br.com.alelo.consumer.consumerpat.dto;


import br.com.alelo.consumer.consumerpat.entity.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
public class ConsumerDTO {

    Integer id;
    String name;
    int documentNumber;
    Date birthDate;

    //contacts
    Integer mobilePhoneNumber;
    Integer residencePhoneNumber;
    Integer phoneNumber;
    String email;

    //Address
    String street;
    int number;
    String city;
    String country;
    int portalCode;

    //cards
    Integer foodCardNumber;
    Integer fuelCardNumber;
    Integer drugstoreNumber;

    public Consumer parseConsumer() {
        Consumer consumer = new Consumer(name, documentNumber, birthDate);
        consumer.addAddress(parseAddress());
        parseContacts().forEach(consumer::addContact);
        parseCard().forEach(consumer::addCard);
        return consumer;
    }

    public Consumer parseConsumerWithoutCard(Integer id) {
        Consumer consumer = new Consumer(name, documentNumber, birthDate);
        consumer.setId(id);
        consumer.addAddress(parseAddress());
        parseContacts().forEach(consumer::addContact);
        return consumer;
    }

    public List<Card> parseCard(){
        List<Card> list = new ArrayList<Card>();
        if(foodCardNumber != null)
            list.add(new Card(foodCardNumber,CardType.FOOD));
        if(fuelCardNumber != null)
            list.add(new Card(fuelCardNumber,CardType.FUEL));
        if(drugstoreNumber != null)
            list.add(new Card(drugstoreNumber,CardType.DRUG));
        return list;
    }

    public List<Contact> parseContacts(){
        List<Contact> list = new ArrayList<Contact>();
        if(mobilePhoneNumber != null)
            list.add(new Contact(ContactType.MobilePhone,mobilePhoneNumber.toString()));
        if(residencePhoneNumber != null)
            list.add(new Contact(ContactType.ResidencePhone,residencePhoneNumber.toString()));
        if(email != null)
            list.add(new Contact(ContactType.Email,email));
        if(phoneNumber != null)
            list.add(new Contact(ContactType.MobilePhone,phoneNumber.toString()));
        return list;
    }

    public Address parseAddress(){
        return new Address(street,number,city,country,portalCode);
    }
}
