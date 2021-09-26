package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.enums.EstablishmentTypeEnum;
import br.com.alelo.consumer.consumerpat.service.dto.response.CardsResponse;

public interface CardsService {

	CardsResponse setBalance(EstablishmentTypeEnum typeEnum, int cardNumber, double value);
}
