package br.com.alelo.consumer.consumerpat.dto;

import br.com.alelo.consumer.consumerpat.enums.EstablishmentType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardsResponseDto extends CardsBaseDto{
    private Integer id;

    @Builder
    public CardsResponseDto(EstablishmentType cardType, int cardNumber, double cardBalance, Integer id) {
        super(cardType, cardNumber, cardBalance);
        this.id = id;
    }
}
