package br.com.alelo.consumer.consumerpat.entity;

import java.util.Date;

public interface Consumer {
    Integer getId();
    String getName();
    int getDocumentNumber();
    Date getBirthDate();
    CustomerContact getContacts();
    Address getAddress();
    Card getFoodCard();
    Card getFuelCard();
    Card getDrugstoreCard();
}
