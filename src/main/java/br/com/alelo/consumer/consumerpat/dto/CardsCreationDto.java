package br.com.alelo.consumer.consumerpat.dto;


import br.com.alelo.consumer.consumerpat.enums.EstablishmentType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardsCreationDto extends CardsBaseDto {

    @Builder
    public CardsCreationDto(EstablishmentType cardType, int cardNumber, double cardBalance) {
        super(cardType, cardNumber, cardBalance);
    }
}
