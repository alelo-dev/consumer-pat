package br.com.alelo.consumer.consumerpat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExtractDTO {

    private int establishmentType;
    private String establishmentName;
    private int cardNumber;
    private String productDescription;
    private double value;
}
