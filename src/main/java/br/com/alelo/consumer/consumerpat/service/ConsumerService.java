package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.domain.dto.ConsumerResponseDTO;
import br.com.alelo.consumer.consumerpat.domain.dto.ExtractResponseDTO;
import br.com.alelo.consumer.consumerpat.domain.entity.Consumer;
import br.com.alelo.consumer.consumerpat.domain.enums.EstablishmentType;

import java.util.List;

public interface ConsumerService {

    List<ConsumerResponseDTO> getAllConsumersList();

    ConsumerResponseDTO saveConsumer(Consumer consumer);

    ConsumerResponseDTO setCardBalence(Integer cardNumber, Double value);

    Consumer findByFoodCardNumber(Integer cardNumber);

    Consumer findByFuelCardNumber(Integer cardNumber);

    Consumer findByDrugstoreNumber(Integer cardNumber);

    ExtractResponseDTO buy(EstablishmentType establishmentType, String establishmentName, Integer cardNumber, String productDescription, Double value);
}
