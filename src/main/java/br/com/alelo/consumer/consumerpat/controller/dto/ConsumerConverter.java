package br.com.alelo.consumer.consumerpat.controller.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.alelo.consumer.consumerpat.entity.Address;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Phone;
import br.com.alelo.consumer.consumerpat.enums.CardType;
import br.com.alelo.consumer.consumerpat.enums.PhoneType;

public class ConsumerConverter {

	public static Consumer toEntity(final CreateConsumerDTO createConsumerDTO) {
		List<Card> cardList = new ArrayList<>();
		List<Phone> phoneList = new ArrayList<>();
		List<Address> addressList = new ArrayList<>();

		createConsumerDTO.getCreateCardDTOS()
				.forEach(createCardDTO -> cardList.add(Card.builder().id(null).CardBalance(createCardDTO.getBalance())
						.CarType(createCardDTO.getCardType().getValue()).CardNumber(createCardDTO.getNumber())
						.createdAt(LocalDateTime.now()).build())

				);
		createConsumerDTO.getCreatePhoneDTOS()
				.forEach(createPhoneDTO -> phoneList
						.add(Phone.builder().id(null).phoneType(createPhoneDTO.getPhoneType().getValue())
								.number(createPhoneDTO.getNumber()).ddd(createPhoneDTO.getDdd()).build())

				);
		createConsumerDTO.getCreateAddressDTOS()
				.forEach(createAddressDTO -> addressList.add(Address.builder().street(createAddressDTO.getStreet())
						.number(createAddressDTO.getNumber()).city(createAddressDTO.getCity())
						.country(createAddressDTO.getCountry()).portalCode(createAddressDTO.getPortalCode()).build()));

		return Consumer.builder().id(null).name(createConsumerDTO.getName())
				.documentNumber(createConsumerDTO.getDocumentNumber()).birthDate(createConsumerDTO.getBirthDate())
				.addressList(addressList).PhoneList(phoneList).email(createConsumerDTO.getEmail())
				.addressList(addressList).cardList(cardList).createdAt(LocalDateTime.now()).build();
	}

	public static ResponseConsumerDTO fromEntity(final Consumer consumer) {

		List<ResponsePhoneDTO> responsePhoneDTOS = new ArrayList<>();
		List<ResponseCardDTO> responseCardDTOS = new ArrayList<>();
		List<ResponseAddressDTO> responseAddressDTOS = new ArrayList<>();

		consumer.getPhoneList()
				.forEach(phone -> responsePhoneDTOS.add(ResponsePhoneDTO.builder().number(phone.getNumber())
						.ddd(phone.getDdd()).phoneType(PhoneType.getEnum(phone.getPhoneType())).build()));

		consumer.getCardList()
				.forEach(card -> responseCardDTOS.add(ResponseCardDTO.builder().number(card.getCardNumber())
						.cardType(CardType.getEnum(card.getCarType())).balance(card.getCardBalance()).build()));

		consumer.getAddressList()
				.forEach(address -> responseAddressDTOS.add(ResponseAddressDTO.builder().street(address.getStreet())
						.number(address.getNumber()).city(address.getCity()).country(address.getCountry())
						.portalCode(address.getPortalCode()).build()));

		return ResponseConsumerDTO.builder().name(consumer.getName()).documentNumber(consumer.getDocumentNumber())
				.birthDate(consumer.getBirthDate()).email(consumer.getEmail()).phoneDTOS(responsePhoneDTOS)
				.addressDTOS(responseAddressDTOS).cardDTOS(responseCardDTOS).build();
	}

}
