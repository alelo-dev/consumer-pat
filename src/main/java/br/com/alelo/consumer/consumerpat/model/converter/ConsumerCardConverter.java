package br.com.alelo.consumer.consumerpat.model.converter;

import java.util.List;
import java.util.stream.Collectors;

import br.com.alelo.consumer.consumerpat.model.dto.ConsumerCardDTO;
import br.com.alelo.consumer.consumerpat.model.entity.ConsumerCard;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConsumerCardConverter {

	public static ConsumerCard toEntity(final ConsumerCardDTO dto) {
		return ConsumerCard.builder()
				.cardNumber(dto.getCardNumber())
				.cardBalance(dto.getCardBalance())
				.cardType(dto.getCardType())
				.build();
	}

	public static ConsumerCardDTO toDTO(final ConsumerCard consumerCard) {
		return ConsumerCardDTO.builder()
				.cardNumber(consumerCard.getCardNumber())
				.cardBalance(consumerCard.getCardBalance())
				.cardType(consumerCard.getCardType())
				.build();
	}

	public static List<ConsumerCardDTO> toListConsumerCardDTO(final List<ConsumerCard> listConsumerCard) {
		return listConsumerCard.stream().map(ConsumerCardConverter::toDTO).collect(Collectors.toList());
	}

}