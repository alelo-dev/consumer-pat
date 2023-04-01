package br.com.alelo.consumer.consumerpat.model;

import br.com.alelo.consumer.consumerpat.entity.CardType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Settling {
    CardType cardType;
    private Integer establishmentType;
    private String establishmentName;
    private String cardNumber;
    private String productDescription;
    private BigDecimal value;
}
