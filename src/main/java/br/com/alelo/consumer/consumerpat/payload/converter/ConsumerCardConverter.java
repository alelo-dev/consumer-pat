package br.com.alelo.consumer.consumerpat.payload.converter;

import java.util.Collection;
import java.util.stream.Collectors;

import br.com.alelo.consumer.consumerpat.model.entity.ConsumerCard;
import br.com.alelo.consumer.consumerpat.payload.ConsumerCardRequest;
import br.com.alelo.consumer.consumerpat.payload.ConsumerCardResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConsumerCardConverter {

	public static ConsumerCard toEntity(final ConsumerCardRequest request) {
		return ConsumerCard.builder()
			.cardNumber(request.getCardNumber())
			.cardType(request.getCardType())
			.build();
	}

	public static ConsumerCardResponse toResponse(final ConsumerCard consumerCard) {
		return ConsumerCardResponse.builder()
			.cardNumber(consumerCard.getCardNumber())
			.cardType(consumerCard.getCardType())
			.cardBalance(consumerCard.getCardBalance())
			.build();
	}

	public static Collection<ConsumerCardResponse> toListConsumerCardResponse(final Collection<ConsumerCard> listConsumerCard) {
		return listConsumerCard.stream().map(ConsumerCardConverter::toResponse).collect(Collectors.toList());
	}
	
	public static Collection<ConsumerCard> toListConsumerCard(final Collection<ConsumerCardRequest> listConsumerCardRequest) {
		return listConsumerCardRequest.stream().map(ConsumerCardConverter::toEntity).collect(Collectors.toList());
	}

}