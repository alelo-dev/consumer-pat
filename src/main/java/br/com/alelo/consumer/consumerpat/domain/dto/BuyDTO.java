package br.com.alelo.consumer.consumerpat.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class BuyDTO {

    private Integer establishmentId;
    private Integer cardNumber;
    private Integer establishmentType;
    private String productDescription;
    private BigDecimal value;

}
