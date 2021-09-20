package br.com.alelo.consumer.consumerpat.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BuyDTO {
    private Integer establishmentType;
    private String establishmentName;
    private Integer cardNumber;
    private String productDescription;
    private Double value;
}

