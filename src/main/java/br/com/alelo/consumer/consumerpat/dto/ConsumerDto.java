package br.com.alelo.consumer.consumerpat.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class ConsumerDto {

    private String name;
    
    private Long documentNumber;
    
    private Date birthDate;

    private ContactDto contact; 

    private AddressDto address;
    
    private List<CardDto> cards;
}
