package br.com.alelo.consumer.consumerpat.core.mapper.domain;

import br.com.alelo.consumer.consumerpat.dataprovider.jpa.entity.ConsumerEntity;
import br.com.alelo.consumer.consumerpat.core.domain.ConsumerDomain;
import br.com.alelo.consumer.consumerpat.core.dto.v1.request.ConsumerCreateV1RequestDto;
import br.com.alelo.consumer.consumerpat.core.dto.v1.request.ConsumerUpdateV1RequestDto;

public class ConsumerDomainMapper {

    public static ConsumerDomain convert(ConsumerCreateV1RequestDto request) {
        if (request == null) {
            return null;
        }

        return ConsumerDomain.builder()
                .address(AddressDomainMapper.convert(request.getAddress()))
                .cards(CardDomainMapper.convert(request.getCards()))
                .contact(ContactDomainMapper.convert(request.getContact()))
                .birthDate(request.getBirthDate())
                .document(request.getDocument())
                .name(request.getName())
                .build();
    }

    public static ConsumerDomain convert(ConsumerEntity entity) {
        if (entity == null) {
            return null;
        }

        return ConsumerDomain.builder()
                .id(entity.getId())
                .address(AddressDomainMapper.convert(entity.getAddress()))
                .birthDate(entity.getBirthDate())
                .contact(ContactDomainMapper.convert(entity.getContact()))
                .name(entity.getName())
                .consumerCode(entity.getConsumerCode())
                .document(entity.getDocument())
                .cards(CardDomainMapper.convertToDomain(entity.getCards()))
                .creationDate(entity.getCreationDate())
                .updateDate(entity.getUpdateDate())
                .build();
    }

    public static ConsumerDomain convertOnlyConsumer(ConsumerEntity entity) {
        if (entity == null) {
            return null;
        }

        return ConsumerDomain.builder()
                .id(entity.getId())
                .birthDate(entity.getBirthDate())
                .name(entity.getName())
                .consumerCode(entity.getConsumerCode())
                .document(entity.getDocument())
                .build();
    }

    public static ConsumerDomain convert(ConsumerUpdateV1RequestDto request, ConsumerDomain consumerDomain) {
        return ConsumerDomain.builder()
                .consumerCode(consumerDomain.getConsumerCode())
                .id(consumerDomain.getId())
                .name(request.getName())
                .document(consumerDomain.getDocument())
                .contact(ContactDomainMapper.convert(request.getContact(), consumerDomain.getContact()))
                .birthDate(request.getBirthDate())
                .cards(consumerDomain.getCards())
                .address(AddressDomainMapper.convert(request.getAddress(), consumerDomain.getAddress()))
                .creationDate(consumerDomain.getCreationDate())
                .updateDate(consumerDomain.getUpdateDate())
                .build();
    }
}
