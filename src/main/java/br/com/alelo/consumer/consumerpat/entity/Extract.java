package br.com.alelo.consumer.consumerpat.entity;

import java.util.Date;
import java.util.UUID;

public interface Extract {

    UUID getId();
    int getEstablishmentNameId();
    String getEstablishmentName();
    String getProductDescription();
    Date getDate();
    String getCardNumber();
    double getValue();

}
