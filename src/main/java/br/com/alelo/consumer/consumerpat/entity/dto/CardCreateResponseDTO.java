package br.com.alelo.consumer.consumerpat.entity.dto;

import br.com.alelo.consumer.consumerpat.entity.enumeration.CardType;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;


@Builder
@Getter
public class CardCreateResponseDTO implements Serializable {

    private Integer cardNumber;
    private CardType cardType;
}
