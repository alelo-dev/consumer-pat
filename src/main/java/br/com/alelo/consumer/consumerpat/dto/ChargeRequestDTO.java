package br.com.alelo.consumer.consumerpat.dto;

import lombok.Data;

@Data
public class ChargeRequestDTO {

    String cardNumber;
    Double value;
    
}
