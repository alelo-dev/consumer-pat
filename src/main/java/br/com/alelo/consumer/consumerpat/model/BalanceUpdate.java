package br.com.alelo.consumer.consumerpat.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BalanceUpdate {
    @NotNull
    private int cardNumber;
    @NotNull
    private double value;
}
