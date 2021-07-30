package br.com.alelo.consumer.consumerpat.dto;

import br.com.alelo.consumer.consumerpat.enumerated.CardType;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class CardDTO {
    private Long id;
    private String number;
    private BigDecimal balance;
    private Set<CardType> types;
}
