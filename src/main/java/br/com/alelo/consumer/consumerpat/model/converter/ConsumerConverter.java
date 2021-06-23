package br.com.alelo.consumer.consumerpat.model.converter;

import java.util.List;
import java.util.stream.Collectors;

import br.com.alelo.consumer.consumerpat.model.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.model.entity.Consumer;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConsumerConverter {

	public static Consumer toEntity(final ConsumerDTO dto) {
		return Consumer.builder()
				.name(dto.getName())
				.documentNumber(dto.getDocumentNumber())
				.birthDate(dto.getBirthDate())
				.mobilePhoneNumber(dto.getMobilePhoneNumber())
				.residencePhoneNumber(dto.getResidencePhoneNumber())
				.phoneNumber(dto.getPhoneNumber())
				.email(dto.getEmail())
				.street(dto.getStreet())
				.number(dto.getNumber())
				.city(dto.getCity())
				.country(dto.getCountry())
				.portalCode(dto.getPortalCode())
				.build();
	}

	public static ConsumerDTO toDTO(final Consumer consumer) {
		return ConsumerDTO.builder()
				.name(consumer.getName())
				.documentNumber(consumer.getDocumentNumber())
				.birthDate(consumer.getBirthDate())
				.mobilePhoneNumber(consumer.getMobilePhoneNumber())
				.residencePhoneNumber(consumer.getResidencePhoneNumber())
				.phoneNumber(consumer.getPhoneNumber())
				.email(consumer.getEmail())
				.street(consumer.getStreet())
				.number(consumer.getNumber())
				.city(consumer.getCity())
				.country(consumer.getCountry())
				.portalCode(consumer.getPortalCode())
				.build();
	}

	public static List<ConsumerDTO> toListConsumerDTO(final List<Consumer> listConsumer) {
		return listConsumer.stream().map(ConsumerConverter::toDTO).collect(Collectors.toList());
	}

}