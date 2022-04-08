package br.com.alelo.consumer.consumerpat.entity;

import java.util.UUID;

public interface Address {
    UUID getId();
    String getStreet();
    String getNumber();
    String getCity();
    String getCountry();
    String getPostalCode();
}
