package br.com.alelo.consumer.consumerpat.requests;

import lombok.Data;

@Data
public class SetCardBalanceRequest {
    public int cardNumber;
    public double value;
}
