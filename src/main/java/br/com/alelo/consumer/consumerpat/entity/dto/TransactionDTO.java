package br.com.alelo.consumer.consumerpat.entity.dto;

import lombok.Builder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public class TransactionDTO implements Serializable {

    private String transactionId;
    private Integer cardNumber;
    private String cardType;
    private BigDecimal value;
    private LocalDateTime transactionDateTime;

}
