package br.com.alelo.consumer.consumerpat.constants;

import br.com.alelo.consumer.consumerpat.entity.Address;
import br.com.alelo.consumer.consumerpat.entity.CardsInfo;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Contact;
import br.com.alelo.consumer.consumerpat.entity.Extract;

import java.util.Date;

public class TestConstants {

    public static Consumer getConsumer() {

        Consumer consumer = new Consumer();

        consumer.setName("Adam Warden");
        consumer.setDocumentNumber(3544390);
        consumer.setBirthDate(new Date());

        consumer.setContact(getContact());
        consumer.setAddress(getAddress());
        consumer.setCard(getCardInfo());

        return consumer;
    }

    public static Extract getExtract() {

        return new Extract("Store",
                "Description",
                new Date(),
                12345,
                45);
    }

    public static Contact getContact() {

        Contact contact = new Contact();

        contact.setMobilePhoneNumber(985218756);
        contact.setResidencePhoneNumber(34459889);
        contact.setEmail("adam.warden@email.com");

        return contact;
    }

    public static Address getAddress() {

        Address address = new Address();

        address.setStreet("Sesame");
        address.setNumber(1);
        address.setCity("Orlando");
        address.setCountry("United States");
        address.setPortalCode(0);

        return address;
    }

    public static CardsInfo getCardInfo() {

        CardsInfo card = new CardsInfo();

        card.setFoodCardNumber(123456);
        card.setFoodCardBalance(500);

        card.setFuelCardNumber(123654);
        card.setFuelCardBalance(500);

        card.setDrugstoreCardNumber(333);
        card.setDrugstoreCardBalance(500);

        return card;
    }

}
