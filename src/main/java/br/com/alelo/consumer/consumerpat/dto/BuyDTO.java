package br.com.alelo.consumer.consumerpat.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class BuyDTO {
    @NonNull
    private EstablishmentDTO establishment;

    @NonNull
    private Long cardNumber;

    @NonNull
    private String productDescription;

    @NonNull
    private Double value;
}
