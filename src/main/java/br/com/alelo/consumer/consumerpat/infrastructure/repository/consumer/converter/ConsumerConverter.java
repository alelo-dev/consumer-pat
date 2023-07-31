package br.com.alelo.consumer.consumerpat.infrastructure.repository.consumer.converter;

import br.com.alelo.consumer.consumerpat.domain.consumer.entity.Address;
import br.com.alelo.consumer.consumerpat.domain.consumer.entity.Consumer;
import br.com.alelo.consumer.consumerpat.domain.consumer.entity.Contact;
import br.com.alelo.consumer.consumerpat.infrastructure.repository.consumer.entity.ConsumerEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static org.springframework.util.ObjectUtils.isEmpty;

@Component
@RequiredArgsConstructor
public class ConsumerConverter {

    public ConsumerEntity toEntity(Consumer consumer) {
        return ConsumerEntity.builder()
                .id(consumer.getId())
                .name(consumer.getName())
                .documentNumber(consumer.getDocumentNumber())
                .birthDate(consumer.getBirthDate())
                .mobilePhoneNumber(consumer.getContact().getMobilePhoneNumber())
                .residencePhoneNumber(isEmpty(consumer.getContact()) ?
                        null : consumer.getContact().getResidencePhoneNumber())
                .phoneNumber(isEmpty(consumer.getContact()) ?
                        null : consumer.getContact().getPhoneNumber())
                .email(isEmpty(consumer.getContact()) ?
                        null : consumer.getContact().getEmail())
                .street(isEmpty(consumer.getAddress()) ?
                        null : consumer.getAddress().getStreet())
                .number(isEmpty(consumer.getAddress()) ?
                        null : consumer.getAddress().getNumber())
                .city(isEmpty(consumer.getAddress()) ?
                        null : consumer.getAddress().getCity())
                .country(isEmpty(consumer.getAddress()) ?
                        null : consumer.getAddress().getCountry())
                .portalCode(isEmpty(consumer.getAddress()) ?
                        null : consumer.getAddress().getPortalCode())
                .build();
    }

    public Consumer toDomain(ConsumerEntity consumerEntity) {
        var contact = new Contact(
                consumerEntity.getMobilePhoneNumber(),
                consumerEntity.getResidencePhoneNumber(),
                consumerEntity.getPhoneNumber(),
                consumerEntity.getEmail()
        );

        var address = new Address(
                consumerEntity.getStreet(),
                consumerEntity.getNumber(),
                consumerEntity.getCity(),
                consumerEntity.getCountry(),
                consumerEntity.getPortalCode()
        );

        var consumer = new Consumer(
                consumerEntity.getName(),
                consumerEntity.getDocumentNumber(),
                consumerEntity.getBirthDate(),
                contact,
                address
        );

        consumer.addId(consumerEntity.getId());

        return consumer;
    }

}
