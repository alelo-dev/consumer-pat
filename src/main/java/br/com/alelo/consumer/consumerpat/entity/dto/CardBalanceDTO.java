package br.com.alelo.consumer.consumerpat.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class CardBalanceDTO {
    private String cardNumber;
    private BigDecimal value;
}
