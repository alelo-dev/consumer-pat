package br.com.alelo.consumer.consumerpat.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO implements Serializable {

    private String transactionId;
    private Integer cardNumber;
    private String cardType;
    private BigDecimal value;
    private LocalDateTime transactionDateTime;

}
