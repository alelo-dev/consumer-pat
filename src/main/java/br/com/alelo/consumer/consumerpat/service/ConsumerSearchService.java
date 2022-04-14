package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.dto.response.ConsumerResponse;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
/*
 *  Deve listar todos os clientes (cerca de 500)
 */
public class ConsumerSearchService {

    @Autowired
    ConsumerRepository consumerRepository;

    public List<ConsumerResponse> listAllConsumers() {

        Pageable firstPageWithFiveHundredConsumers = PageRequest.of(0, 500);

        Page<Consumer> allConsumersPage = consumerRepository.findAll(firstPageWithFiveHundredConsumers);

        return allConsumersPage.stream()
                .map(this::convertConsumer)
                .collect(Collectors.toList());
    }

    private ConsumerResponse convertConsumer(final Consumer consumer) {
        return ConsumerResponse.builder()
                .id(consumer.getId())
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
                .postalCode(consumer.getPostalCode())
                .foodCardNumber(consumer.getFoodCardNumber())
                .foodCardBalance(consumer.getFoodCardBalance())
                .fuelCardNumber(consumer.getFuelCardNumber())
                .fuelCardBalance(consumer.getFuelCardBalance())
                .drugstoreNumber(consumer.getDrugstoreNumber())
                .drugstoreCardBalance(consumer.getDrugstoreCardBalance())
                .build();
    }
}
