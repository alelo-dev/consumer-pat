package br.com.alelo.consumer.consumerpat.infra.converter;

import br.com.alelo.consumer.consumerpat.controller.response.ConsumerResponse;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import org.springframework.stereotype.Component;

@Component
public class ConsumerToConsumerResponseConverter implements Converter<Consumer, ConsumerResponse> {
    @Override
    public ConsumerResponse convert(final Consumer domain) {
        return ConsumerResponse
                .builder()
                .id(domain.getId())
                .documentNumber(domain.getDocumentNumber())
                .name(domain.getName())
                .birthDate(domain.getBirthDate())
                .build();
    }
}
