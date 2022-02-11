package br.com.alelo.consumer.consumerpat.dto;

import java.math.BigDecimal;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.CardType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class CardDTO {

    private Long id;
    private String cardNumber;
    private CardType cardType;
    private BigDecimal balance;

    public CardDTO() {
        
    }
    public CardDTO(Card entity) {
        this.id = entity.getId();
        this.balance = entity.getBalance();
        this.cardNumber = entity.getCardNumber();
        this.cardType = entity.getCardType();
    }  
    
}
