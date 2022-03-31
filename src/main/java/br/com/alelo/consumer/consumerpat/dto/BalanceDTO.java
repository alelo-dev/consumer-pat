package br.com.alelo.consumer.consumerpat.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BalanceDTO {
    @NonNull
    private Long cardNumber;

    @NonNull
    private Double value;
}
