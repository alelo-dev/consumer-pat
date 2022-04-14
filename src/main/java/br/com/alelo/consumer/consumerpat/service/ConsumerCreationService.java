package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.dto.request.ConsumerCardRequestDto;
import br.com.alelo.consumer.consumerpat.dto.request.ConsumerRequestDto;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.type.CardType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConsumerCreationService {

    @Autowired
    ConsumerRepository repository;

    public void createConsumer(ConsumerRequestDto consumerRequest) {
        Consumer consumer = this.convertConsumer(consumerRequest);
        repository.save(consumer);
    }


    private Consumer convertConsumer(final ConsumerRequestDto consumerRequest) {
        Consumer consumer = new Consumer();

        consumer.setId(consumerRequest.getId());
        consumer.setName(consumerRequest.getName());
        consumer.setDocumentNumber(consumerRequest.getDocumentNumber());
        consumer.setBirthDate(consumerRequest.getBirthDate());

        consumer.setMobilePhoneNumber(consumerRequest.getContacts().getMobilePhoneNumber());
        consumer.setResidencePhoneNumber(consumerRequest.getContacts().getResidencePhoneNumber());
        consumer.setPhoneNumber(consumerRequest.getContacts().getWorkPhoneNumber());
        consumer.setEmail(consumerRequest.getContacts().getEmail());

        consumer.setStreet(consumerRequest.getAddress().getStreet());
        consumer.setNumber(consumerRequest.getAddress().getNumber());
        consumer.setCity(consumerRequest.getAddress().getCity());
        consumer.setCountry(consumerRequest.getAddress().getCountry());
        consumer.setPostalCode(consumerRequest.getAddress().getPostalCode());

        Optional<ConsumerCardRequestDto> foodCard = consumerRequest.getCardType(consumerRequest, CardType.FOOD);
        if (foodCard.isPresent()) {
            consumer.setFoodCardNumber(foodCard.get().getNumber());
            consumer.setFoodCardBalance(foodCard.get().getBalance());
        }

        Optional<ConsumerCardRequestDto> fuelCard = consumerRequest.getCardType(consumerRequest, CardType.FUEL);
        if (fuelCard.isPresent()) {
            consumer.setFuelCardNumber(fuelCard.get().getNumber());
            consumer.setFuelCardBalance(fuelCard.get().getBalance());
        }

        Optional<ConsumerCardRequestDto> drugstoreCard = consumerRequest.getCardType(consumerRequest, CardType.DRUGSTORE);
        if (drugstoreCard.isPresent()) {
            consumer.setDrugstoreNumber(drugstoreCard.get().getNumber());
            consumer.setDrugstoreCardBalance(drugstoreCard.get().getBalance());
        }

        return consumer;
    }
}
