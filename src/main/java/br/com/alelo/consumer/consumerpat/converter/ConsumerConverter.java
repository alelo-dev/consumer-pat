package br.com.alelo.consumer.consumerpat.converter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.alelo.consumer.consumerpat.dto.CardConsumerRequestDTO;
import br.com.alelo.consumer.consumerpat.dto.ConsumerRequestDTO;
import br.com.alelo.consumer.consumerpat.dto.ConsumerResponseDTO;
import br.com.alelo.consumer.consumerpat.entity.CardConsumer;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.util.FormatDateUtil;

public class ConsumerConverter {

	public static Consumer toEntity(ConsumerRequestDTO consumerRequestDTO) {


		Consumer consumer = new Consumer();
		consumer.setId(consumerRequestDTO.getId());
		consumer.setName(consumerRequestDTO.getName());
		consumer.setDocumentNumber(consumerRequestDTO.getDocumentNumber());
		consumer.setCreatedAt(LocalDateTime.now());

		if(consumerRequestDTO.getBirthDate()!= null) {
			consumer.setBirthDate(FormatDateUtil.stringToLocalDate(consumerRequestDTO.getBirthDate()));
		}

		consumer.setEmail(consumerRequestDTO.getEmail());

		if(consumerRequestDTO.getContact()!= null) {
			consumer.setContact(ContactConverter.toEntity(consumerRequestDTO.getContact()));
		}

		if(consumerRequestDTO.getAddress()!=null) {
			consumer.setAddress(AddressConverter.toEntity(consumerRequestDTO.getAddress()));
		}

		if(consumerRequestDTO.getCards() != null) {

			List<CardConsumer> listCardConsumer = new ArrayList<CardConsumer>();
			for (CardConsumerRequestDTO cardConsumerRequestDTO : consumerRequestDTO.getCards()) {

				CardConsumer cardConsumer = CardConsumerConverter.toEntity(cardConsumerRequestDTO);
				cardConsumer.setConsumer(consumer);
				listCardConsumer.add(cardConsumer);
			}
			consumer.setCards(listCardConsumer);
		}

		return consumer;
	}


	public static ConsumerResponseDTO toDTO(Consumer consumer) {


		ConsumerResponseDTO consumerResponse = new ConsumerResponseDTO();
		consumerResponse.setId(consumer.getId());
		consumerResponse.setName(consumer.getName());
		consumerResponse.setDocumentNumber(consumer.getDocumentNumber());
		consumerResponse.setBirthDate(FormatDateUtil.localDateToString(consumer.getBirthDate()));
		consumerResponse.setEmail(consumer.getEmail());

		consumerResponse.setConsumerContact(ContactConverter.toDTO(consumer.getContact()));
		consumerResponse.setAddress(AddressConverter.toDTO(consumer.getAddress()));
		consumerResponse.setCards(CardConsumerConverter.toDTO(consumer.getCards()));

		return consumerResponse;
	}

	public static Consumer updateTarget(final Consumer target, final ConsumerRequestDTO origin) {

		if(target != null) {
			target.setName(origin.getName());
			target.setDocumentNumber(origin.getDocumentNumber());

			if(origin.getBirthDate() != null) {
				target.setBirthDate(FormatDateUtil.stringToLocalDate(origin.getBirthDate()));
			}
			if(origin.getContact()!= null) {
				target.setContact(ContactConverter.toEntity(origin.getContact()));
			}

			if(origin.getAddress()!=null) {
				target.setAddress(AddressConverter.toEntity(origin.getAddress()));
			}

			if(origin.getCards() != null) {

				List<CardConsumer> listCardConsumer = new ArrayList<CardConsumer>();
				for (CardConsumerRequestDTO cardConsumerRequestDTO : origin.getCards()) {

					CardConsumer cardConsumer = CardConsumerConverter.toEntity(cardConsumerRequestDTO);
					cardConsumer.setConsumer(target);
					listCardConsumer.add(cardConsumer);
				}

				target.setCards(listCardConsumer);
			}
		}

		return target;

	}

}
