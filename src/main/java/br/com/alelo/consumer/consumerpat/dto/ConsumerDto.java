package br.com.alelo.consumer.consumerpat.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ConsumerDto {

    private String name;
    
    private Long documentNumber;
    
    private Date birthDate;

    private ContactDto contact; 

    private AddressDto address;
    
    private List<CardDto> cards;
}
