package br.com.alelo.consumer.consumerpat.dto;

import br.com.alelo.consumer.consumerpat.enums.CardType;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class SaleDto {

    private Integer establishmentType;
    private String establishmentName;
    private Long cardNumber;
    private String productDescription;
    private BigDecimal value;

    public CardType getCardType() {
        switch (this.establishmentType) {
            case 1:
                return CardType.FOOD;
            case 2:
                return CardType.DRUGSSTORE;
            case 3:
                return CardType.FUEL;
            default:
                return null;
        }

    }

}
