package br.com.alelo.consumer.consumerpat.service;

import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.dto.CardBalanceDTO;

@Service
public interface ICardService {

	void setCardBalance(CardBalanceDTO cardBalanceDTO);
}
