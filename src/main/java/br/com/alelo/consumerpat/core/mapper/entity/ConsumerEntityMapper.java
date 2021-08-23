package br.com.alelo.consumerpat.core.mapper.entity;

import br.com.alelo.consumerpat.core.domain.ConsumerDomain;
import br.com.alelo.consumerpat.dataprovider.jpa.entity.ConsumerEntity;

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
                .build();
    }
}
