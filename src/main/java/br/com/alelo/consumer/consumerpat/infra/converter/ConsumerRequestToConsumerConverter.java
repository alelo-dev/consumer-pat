package br.com.alelo.consumer.consumerpat.infra.converter;

import br.com.alelo.consumer.consumerpat.controller.request.ConsumerRequest;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import org.springframework.stereotype.Component;

@Component
public class ConsumerRequestToConsumerConverter implements Converter<ConsumerRequest, Consumer>{
    @Override
    public Consumer convert(ConsumerRequest domain) {
        return Consumer.builder()
                .name(domain.getName())
                .documentNumber(domain.getDocumentNumber())
                .birthDate(domain.getBirthDate()).build();
    }
}
