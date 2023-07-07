package br.com.alelo.consumer.consumerpat.requests;

import lombok.Data;

import javax.validation.constraints.Positive;

@Data
public class SetCardBalanceRequest {
    @Positive(message = "Numero de cartão inválido")
    public int cardNumber;
    @Positive(message = "Só é possível inserir valores maiores que zero")
    public double value;
}
