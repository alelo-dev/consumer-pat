package br.com.alelo.consumer.consumerpat.controller;

import lombok.Data;

@Data
public class BuyRequest {
    private int establishmentType;
    private String establishmentName;
    private int cardNumber;
    private String productDescription;
    private double value;
}
