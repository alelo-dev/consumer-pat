package br.com.alelo.consumer.consumerpat.dto;

import lombok.Data;

@Data
public class BuyDTO {
    private Long establishmentType;
    private String establishmentName;
    private Integer cardNumber;
    private String productDescription;
    private Double value;
}
