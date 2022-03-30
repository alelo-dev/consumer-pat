package br.com.alelo.consumer.consumerpat.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BuyDTO {
    private EstablishmentDTO establishment;

    private Long cardNumber;

    private String productDescription;

    private Double value;
}
