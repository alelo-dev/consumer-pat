package br.com.alelo.consumer.consumerpat.services;

import br.com.alelo.consumer.consumerpat.dtos.CardsDto;
import br.com.alelo.consumer.consumerpat.models.enums.EstablishmentEnum;

public interface CardsService {
    CardsDto setBalance(EstablishmentEnum establishmentEnum, int cardNumber, double value);
}
