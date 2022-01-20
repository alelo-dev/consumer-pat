package br.com.alelo.consumer.consumerpat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BuyRequest {
    private int establishmentType;
    private String establishmentName;
    private int cardNumber;
    private String productDescription;
    private double value;
}
