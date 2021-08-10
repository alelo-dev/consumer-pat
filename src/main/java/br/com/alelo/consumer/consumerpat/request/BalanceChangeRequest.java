package br.com.alelo.consumer.consumerpat.request;

import lombok.Data;

@Data
public class BalanceChangeRequest {
    int cardNumber;
    double value;
}
