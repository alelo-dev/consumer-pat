package br.com.alelo.consumer.consumerpat.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class ProductBuyDTO {

    private String establishmentType;
    private String establishmentName;
    private String cardNumber;
    private String productDescription;
    private BigDecimal value;
    private int amountBuyed;
}
