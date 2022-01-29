package br.com.alelo.consumer.consumerpat.entity.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class BuyDTO {

    private int establishmentId;
    private int cardNumber;
    private int establishmentType;
    private String productDescription;
    private BigDecimal value;

}
