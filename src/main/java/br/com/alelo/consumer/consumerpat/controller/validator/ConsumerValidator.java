package br.com.alelo.consumer.consumerpat.controller.validator;

import java.util.Objects;

import br.com.alelo.consumer.consumerpat.controller.dto.CreateConsumerDTO;

public class ConsumerValidator {

	public static String validate(final CreateConsumerDTO createConsumerDTO) {

		if (Objects.isNull(createConsumerDTO.getName())) {
			return "Nome não pode ser null";
		}
		if (Objects.isNull(createConsumerDTO.getDocumentNumber())) {
			return "Numero do Documento  não pode ser null";
		}
		if (Objects.isNull(createConsumerDTO.getCreateCardDTOS())) {
			return "Cartao   não pode ser null";
		}
		if (createConsumerDTO.getCreateCardDTOS().stream()
				.anyMatch(createCardDTO -> Objects.isNull(createCardDTO.getCardType()))) {
			return "Tipo do cartâo não pode ser null";
		}
		if (createConsumerDTO.getCreateCardDTOS().stream()
				.anyMatch(createCardDTO -> Objects.isNull(createCardDTO.getNumber()))) {
			return "numero do cartâo não pode ser null";
		}
		if (Objects.isNull(createConsumerDTO.getCreatePhoneDTOS())) {
			return "Telefone não pode ser null";
		}
		if (createConsumerDTO.getCreatePhoneDTOS().stream()
				.anyMatch(createCardDTO -> Objects.isNull(createCardDTO.getPhoneType()))) {
			return "phoneType não pode ser null";
		}
		return null;
	}

}
