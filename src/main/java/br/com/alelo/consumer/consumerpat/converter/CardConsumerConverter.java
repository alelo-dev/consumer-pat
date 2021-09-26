package br.com.alelo.consumer.consumerpat.converter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.alelo.consumer.consumerpat.dto.CardConsumerRequestDTO;
import br.com.alelo.consumer.consumerpat.dto.CardConsumerResponseDTO;
import br.com.alelo.consumer.consumerpat.entity.CardConsumer;
import br.com.alelo.consumer.consumerpat.entity.CardType;

public class CardConsumerConverter {

	public static CardConsumer toEntity(CardConsumerRequestDTO cardRequestDTO) {

		CardType cardType = new CardType();
		cardType.setId(cardRequestDTO.getTypeCard());

		CardConsumer cardConsumer = new CardConsumer();
		cardConsumer.setCardType(cardType);
		cardConsumer.setBalance(cardRequestDTO.getBalance());
		cardConsumer.setCardNumber(cardRequestDTO.getCardNumber());
		cardConsumer.setCreatedAt(LocalDateTime.now());

		return cardConsumer;
	}

	public static List<CardConsumerResponseDTO> toDTO(List<CardConsumer> listCardConsumer) {

		List<CardConsumerResponseDTO> listCardConsumerResponseDTO = new ArrayList<CardConsumerResponseDTO>();

		for (CardConsumer cardConsumer : listCardConsumer) {
			listCardConsumerResponseDTO.add(CardConsumerConverter.toDTO(cardConsumer));
		}

		return listCardConsumerResponseDTO;
	}

	public static CardConsumerResponseDTO toDTO(CardConsumer cardConsumer) {

		return CardConsumerResponseDTO.builder().balance(cardConsumer.getBalance())
		.cardNumber(cardConsumer.getCardNumber())
		.typeCard(cardConsumer.getCardType().getName()).build();
	}


}
