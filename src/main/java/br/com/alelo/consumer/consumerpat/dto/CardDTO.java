package br.com.alelo.consumer.consumerpat.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CardDTO {
    private String number;
    private BigDecimal balance;
    private Long cardTypeId;

}
