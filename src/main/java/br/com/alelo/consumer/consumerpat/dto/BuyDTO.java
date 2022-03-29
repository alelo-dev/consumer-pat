package br.com.alelo.consumer.consumerpat.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BuyDTO {
    Integer establishmentType;

    String establishmentName;

    Integer cardNumber;

    String productDescription;

    Double value;
}
