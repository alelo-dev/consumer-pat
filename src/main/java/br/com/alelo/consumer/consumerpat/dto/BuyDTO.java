package br.com.alelo.consumer.consumerpat.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
