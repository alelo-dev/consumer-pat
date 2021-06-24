package br.com.alelo.consumer.consumerpat.dto;

import br.com.alelo.consumer.consumerpat.enums.EstablishmentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
class CardsBaseDto {
    private EstablishmentType cardType;
    private int cardNumber;
    private double cardBalance;
}
