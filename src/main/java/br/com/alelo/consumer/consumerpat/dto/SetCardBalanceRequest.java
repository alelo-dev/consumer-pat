package br.com.alelo.consumer.consumerpat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SetCardBalanceRequest {
    private int cardNumber;
    private double value;
}
