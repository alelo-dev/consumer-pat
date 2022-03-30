package br.com.alelo.consumer.consumerpat.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BalanceDTO {
    private Long cardNumber;

    private Double value;
}
