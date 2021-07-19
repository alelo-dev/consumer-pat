package br.com.alelo.consumer.consumerpat.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BuyCardBalanceDTO {

    private int establishmentType;
    private Long establishmentId;
    private String establishmentName;
    private String cardNumber;
    private String productDescription;
    private BigDecimal value;
}
