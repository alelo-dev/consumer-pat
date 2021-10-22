package br.com.alelo.consumer.consumerpat.api.dto.input;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsumerInput {

    private String name;
    private String document;
    private Date birthDate;
    private ContactInput contact;
    private AddressInput address;
    private List<CardInput> cards;
}