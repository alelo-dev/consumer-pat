package br.com.alelo.consumer.consumerpat.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuyCreate {
    int establishmentType;
    String establishmentName;
    int cardNumber;
    String productDescription;
    double value;
}
