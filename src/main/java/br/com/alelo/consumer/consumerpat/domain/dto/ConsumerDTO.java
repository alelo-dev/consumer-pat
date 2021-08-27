package br.com.alelo.consumer.consumerpat.domain.dto;

import java.util.Date;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConsumerDTO {

    private Integer id;
    private String name;
    private int documentNumber;
    private Date birthDate;
    private ContactDTO contact;
    private AddressDTO address;
    private Set<CardDTO> cards;

	
}
