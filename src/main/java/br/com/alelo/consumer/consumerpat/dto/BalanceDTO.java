package br.com.alelo.consumer.consumerpat.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class BalanceDTO {
    @NonNull
    private Long cardNumber;

    @NonNull
    private Double value;
}
