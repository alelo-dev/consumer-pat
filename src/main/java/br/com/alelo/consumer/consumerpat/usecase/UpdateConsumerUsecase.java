package br.com.alelo.consumer.consumerpat.usecase;

import br.com.alelo.consumer.consumerpat.controller.model.ConsumerRequest;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateConsumerUsecase {

    private final ConsumerRepository repository;

    public void execute(ConsumerRequest consumerRequest, Integer id) {
        Optional<Consumer> consumerOpt = repository.findById(id);

        if (consumerOpt.isPresent()) {
            repository.save(buildUpdateConsumer(consumerOpt.get(), consumerRequest));
        }
    }

    private Consumer buildUpdateConsumer(Consumer consumer, ConsumerRequest consumerRequest) {
        return Consumer.builder()
            .id(consumer.getId())
            .name(consumerRequest.getName())
            .documentNumber(consumerRequest.getDocumentNumber())
            .birthDate(consumerRequest.getBirthDate())
            .mobilePhoneNumber(consumerRequest.getMobilePhoneNumber())
            .residencePhoneNumber(consumerRequest.getResidencePhoneNumber())
            .phoneNumber(consumerRequest.getPhoneNumber())
            .email(consumerRequest.getEmail())
            .street(consumerRequest.getStreet())
            .number(consumerRequest.getNumber())
            .city(consumerRequest.getCity())
            .country(consumerRequest.getCountry())
            .portalCode(consumerRequest.getPortalCode())
            .foodCardNumber(consumer.getFoodCardNumber())
            .foodCardBalance(consumer.getFoodCardBalance())
            .fuelCardNumber(consumer.getFuelCardNumber())
            .fuelCardBalance(consumer.getFuelCardBalance())
            .drugstoreNumber(consumer.getDrugstoreNumber())
            .drugstoreCardBalance(consumer.getDrugstoreCardBalance())
            .build();
    }
}
