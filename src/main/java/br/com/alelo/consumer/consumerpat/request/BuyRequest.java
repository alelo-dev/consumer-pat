package br.com.alelo.consumer.consumerpat.request;

import lombok.Data;

@Data
public class BuyRequest {
    int establishmentType;
    String establishmentName;
    int cardNumber;
    String productDescription;
    double value;
}
