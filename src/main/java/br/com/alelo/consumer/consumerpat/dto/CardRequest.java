package br.com.alelo.consumer.consumerpat.dto;

import br.com.alelo.consumer.consumerpat.entity.TypeCard;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CardRequest {
    private String idCard;
    private Long cardNumber;
    private BigDecimal cardBalance;
    private TypeCard typeCard;
}
