package br.com.alelo.consumer.consumerpat.parameter;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BuyParameter {

    private int consumerId;
    private int establishmentId;

    private String productDescription;
    private BigDecimal productValue;

    private String cardNumber;
}
