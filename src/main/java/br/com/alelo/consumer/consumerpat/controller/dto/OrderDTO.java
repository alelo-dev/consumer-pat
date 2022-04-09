package br.com.alelo.consumer.consumerpat.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderDTO {

    private String cardNumber;
    private double value;
    private String establishmentName;
    private String productDescription;

}
