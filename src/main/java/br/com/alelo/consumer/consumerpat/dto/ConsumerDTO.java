package br.com.alelo.consumer.consumerpat.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class ConsumerDTO implements Serializable {
    private Long id;
    private String name;
    private Integer documentNumber;
    private Date birthDate;
    private List<ContactDTO> contacts;
    private AddressDTO address;
    private List<CardDTO> cards;
}
