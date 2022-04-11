package br.com.alelo.consumer.consumerpat.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class ConsumerDto {

    private String name;
    private int documentNumber;
    private Date birthDate;
    private ContactDto contactDto;
    private AddressDto addressDto;
    private CardsDto cardsDto;
}
