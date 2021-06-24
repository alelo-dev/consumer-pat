package br.com.alelo.consumer.consumerpat.payload.converter;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import br.com.alelo.consumer.consumerpat.model.entity.Consumer;
import br.com.alelo.consumer.consumerpat.payload.ConsumerRequest;
import br.com.alelo.consumer.consumerpat.payload.ConsumerResponse;
import br.com.alelo.consumer.consumerpat.payload.ConsumerUpdateRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConsumerConverter {

	public static Consumer toEntity(final ConsumerRequest request) {
		return Consumer.builder()
			.name(request.getName())
			.documentNumber(request.getDocumentNumber())
			.birthDate(request.getBirthDate())
			.mobilePhoneNumber(request.getMobilePhoneNumber())
			.residencePhoneNumber(request.getResidencePhoneNumber())
			.phoneNumber(request.getPhoneNumber())
			.email(request.getEmail())
			.consumerAddress(ConsumerAddressConverter.toEntity(request.getConsumerAddress()))
			.consumerCards(ConsumerCardConverter.toListConsumerCard(request.getConsumerCards()))
			.build();
	}
	
	public static Consumer toEntity(final ConsumerUpdateRequest request) {
		return Consumer.builder()
			.name(request.getName())
			.documentNumber(request.getDocumentNumber())
			.birthDate(request.getBirthDate())
			.mobilePhoneNumber(request.getMobilePhoneNumber())
			.residencePhoneNumber(request.getResidencePhoneNumber())
			.phoneNumber(request.getPhoneNumber())
			.email(request.getEmail())
			.consumerAddress(ConsumerAddressConverter.toEntity(request.getConsumerAddress()))
			.build();
	}

	public static ConsumerResponse toResponse(final Consumer consumer) {
		return ConsumerResponse.builder()
			.name(consumer.getName())
			.documentNumber(consumer.getDocumentNumber())
			.birthDate(consumer.getBirthDate())
			.mobilePhoneNumber(consumer.getMobilePhoneNumber())
			.residencePhoneNumber(consumer.getResidencePhoneNumber())
			.phoneNumber(consumer.getPhoneNumber())
			.email(consumer.getEmail())
			.consumerAddress(ConsumerAddressConverter.toResponse(consumer.getConsumerAddress()))
			.consumerCards(ConsumerCardConverter.toListConsumerCardResponse(consumer.getConsumerCards()))
			.build();
	}

	public static List<ConsumerResponse> toListConsumerConsumerResponse(final List<Consumer> listConsumer) {
		return listConsumer.stream().map(ConsumerConverter::toResponse).collect(Collectors.toList());
	}
	
	public static Collection<Consumer> toListConsumerCard(final Collection<ConsumerRequest> listConsumerRequest) {
		return listConsumerRequest.stream().map(ConsumerConverter::toEntity).collect(Collectors.toList());
	}

}