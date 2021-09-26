package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.dto.CardConsumerRequestDTO;
import br.com.alelo.consumer.consumerpat.dto.ChargeRequestDTO;
import br.com.alelo.consumer.consumerpat.entity.CardConsumer;
import br.com.alelo.consumer.consumerpat.entity.Consumer;

public interface ICardService {
	
	public CardConsumer findByNumber(final String cardNumber);
	
	public void addCardconsumer(Consumer consumer, CardConsumerRequestDTO cardConsumerRequestDTO);

	public void chargeCardConsumer(Long consumerId, ChargeRequestDTO chargeDTO);
}
