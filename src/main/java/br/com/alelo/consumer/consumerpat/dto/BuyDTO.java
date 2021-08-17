package br.com.alelo.consumer.consumerpat.dto;

import br.com.alelo.consumer.consumerpat.entity.CardType;
import lombok.Data;

@Data
public class BuyDTO {
    private CardType cardType;
    private Integer cardNumber;
    private String productDescription;
    private Double value;
}
