package br.com.alelo.consumer.consumerpat.core.mapper.entity;

import br.com.alelo.consumer.consumerpat.dataprovider.jpa.entity.ConsumerEntity;
import br.com.alelo.consumer.consumerpat.core.domain.ConsumerDomain;

public class ConsumerEntityMapper {

    public static ConsumerEntity convertOnlyConsumer(ConsumerDomain domain) {
        if (domain == null) {
            return null;
        }

        return ConsumerEntity.builder()
                .id(domain.getId())
                .consumerCode(domain.getConsumerCode())
                .birthDate(domain.getBirthDate())
                .document(domain.getDocument())
                .name(domain.getName())
                .creationDate(domain.getCreationDate())
                .updateDate(domain.getUpdateDate())
                .build();
    }
}
