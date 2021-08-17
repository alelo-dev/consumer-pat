package br.com.alelo.consumer.consumerpat.dto;

import br.com.alelo.consumer.consumerpat.entity.CardType;
import lombok.Data;

import java.io.Serializable;

@Data
public class UpdateCardDTO implements Serializable {
    private Long id;
    private Integer cardNumber;
    private CardType cardType;
}
