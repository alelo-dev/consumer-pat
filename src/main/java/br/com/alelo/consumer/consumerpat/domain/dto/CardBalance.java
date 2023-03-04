package br.com.alelo.consumer.consumerpat.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CardBalance {
    private int consumerId;
    private String cardNumber;
    private double value;

}
