package br.com.alelo.consumer.consumerpat.service.impl;

import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.controller.dto.ConsumerConverter;
import br.com.alelo.consumer.consumerpat.controller.dto.ResponseConsumerDTO;
import br.com.alelo.consumer.consumerpat.controller.dto.UpdateConsumerDTO;
import br.com.alelo.consumer.consumerpat.entity.Address;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Phone;
import br.com.alelo.consumer.consumerpat.exceptions.CardNumberAlreadyExistsException;
import br.com.alelo.consumer.consumerpat.exceptions.ConsumerDocException;
import br.com.alelo.consumer.consumerpat.exceptions.ConsumerNotFoundException;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConsumerServiceImpl implements ConsumerService {

	private final ConsumerRepository consumerRepository;
	private final CardRepository cardRepository;

	@Override
	public Consumer createConsumer(Consumer consumer) {
		consumerRepository.findByDocumentNumber(consumer.getDocumentNumber()).ifPresent(consumerPresent -> {
			throw new ConsumerDocException();
		});

		consumer.getCardList()
				.forEach(card -> cardRepository.findCardByNumber(card.getCardNumber()).ifPresent(cardPresent -> {
					throw new CardNumberAlreadyExistsException();
				}));

		return consumerRepository.save(consumer);
	}

	@Override
	public Consumer updateConsumer(Integer id, UpdateConsumerDTO updateConsumerDTO) {

		Optional<Consumer> consumer = consumerRepository.findById(id);
		if (consumer.isPresent()) {
			Consumer consumerEntity = consumer.get();
			List<Phone> phoneList = new ArrayList<>();
			List<Address> addressList = new ArrayList<>();

			updateConsumerDTO.getUpdatePhoneDTOS()
					.forEach(updatePhoneDTO -> phoneList
							.add(Phone.builder().id(null).phoneType(updatePhoneDTO.getPhoneType().getValue())
									.number(updatePhoneDTO.getNumber()).ddd(updatePhoneDTO.getDdd()).build())

					);

			updateConsumerDTO.getUpdateAddressDTOS()
					.forEach(updateAddressDTO -> addressList.add(
							Address.builder().street(updateAddressDTO.getStreet()).number(updateAddressDTO.getNumber())
									.city(updateAddressDTO.getCity()).country(updateAddressDTO.getCountry())
									.portalCode(updateAddressDTO.getPortalCode()).build()));

			return consumerRepository
					.save(Consumer.builder().id(consumerEntity.getId()).createdAt(consumerEntity.getCreatedAt())
							.name(updateConsumerDTO.getName()).birthDate(updateConsumerDTO.getBirthDate())
							.updatedAt(LocalDateTime.now()).addressList(addressList).PhoneList(phoneList).build());
		} else {
			throw new ConsumerNotFoundException();
		}
	}

	@Override
	public Page<ResponseConsumerDTO> getPageConsumer(Pageable pageable) {

		return consumerRepository.findAll(pageable).map(ConsumerConverter::fromEntity);
	}

}
