package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.dto.request.ConsumerCardRequestDto;
import br.com.alelo.consumer.consumerpat.dto.request.ConsumerRequestDto;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.type.CardType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Não deve ser possível alterar o saldo do cartão
 */
@Service
public class ConsumerUpdateService {

    @Autowired
    ConsumerRepository consumerRepository;

    public void updateConsumer(ConsumerRequestDto consumerRequest) {
        Optional<Consumer> consumer = consumerRepository.findById(consumerRequest.getId());
        if (consumer.isEmpty()) {
            throw new RuntimeException("NOT FOUND");
        }

        consumerRepository.save(this.updateConsumer(consumer.get(), consumerRequest));
    }


    private Consumer updateConsumer(Consumer existingConsumer, final ConsumerRequestDto consumerUpdateRequest) {
        existingConsumer.setName(consumerUpdateRequest.getName());
        existingConsumer.setDocumentNumber(consumerUpdateRequest.getDocumentNumber());
        existingConsumer.setBirthDate(consumerUpdateRequest.getBirthDate());

        existingConsumer.setMobilePhoneNumber(consumerUpdateRequest.getContacts().getMobilePhoneNumber());
        existingConsumer.setResidencePhoneNumber(consumerUpdateRequest.getContacts().getResidencePhoneNumber());
        existingConsumer.setPhoneNumber(consumerUpdateRequest.getContacts().getWorkPhoneNumber());
        existingConsumer.setEmail(consumerUpdateRequest.getContacts().getEmail());

        existingConsumer.setStreet(consumerUpdateRequest.getAddress().getStreet());
        existingConsumer.setNumber(consumerUpdateRequest.getAddress().getNumber());
        existingConsumer.setCity(consumerUpdateRequest.getAddress().getCity());
        existingConsumer.setCountry(consumerUpdateRequest.getAddress().getCountry());
        existingConsumer.setPostalCode(consumerUpdateRequest.getAddress().getPostalCode());

        Optional<ConsumerCardRequestDto> foodCard = consumerUpdateRequest.getCardType(consumerUpdateRequest, CardType.FOOD);
        foodCard.ifPresent(consumerCardRequestDto -> existingConsumer.setFoodCardNumber(consumerCardRequestDto.getNumber()));

        Optional<ConsumerCardRequestDto> fuelCard = consumerUpdateRequest.getCardType(consumerUpdateRequest, CardType.FUEL);
        fuelCard.ifPresent(consumerCardRequestDto -> existingConsumer.setFuelCardNumber(consumerCardRequestDto.getNumber()));

        Optional<ConsumerCardRequestDto> drugstoreCard = consumerUpdateRequest.getCardType(consumerUpdateRequest, CardType.DRUGSTORE);
        drugstoreCard.ifPresent(consumerCardRequestDto -> existingConsumer.setDrugstoreNumber(consumerCardRequestDto.getNumber()));

        return existingConsumer;
    }
}
