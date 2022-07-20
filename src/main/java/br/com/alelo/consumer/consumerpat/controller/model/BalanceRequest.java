package br.com.alelo.consumer.consumerpat.controller.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class BalanceRequest {
    private final int cardNumber;
    private final double value;
}
