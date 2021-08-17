package br.com.alelo.consumer.consumerpat.dto;

import br.com.alelo.consumer.consumerpat.entity.CardType;
import lombok.Data;

import javax.validation.constraints.Positive;

@Data
public class BuyDTO {
    private CardType cardType;
    private Integer cardNumber;
    private String productDescription;
    @Positive
    private Double value;
}
