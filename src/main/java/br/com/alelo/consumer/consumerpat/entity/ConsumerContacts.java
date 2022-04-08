package br.com.alelo.consumer.consumerpat.entity;

import java.util.UUID;

public interface ConsumerContacts {
    UUID getId();
    String getMobilePhoneNumber();
    String getResidencePhoneNumber();
    String getPhoneNumber();
    String getEmail();
}
