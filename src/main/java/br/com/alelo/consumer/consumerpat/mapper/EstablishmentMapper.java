package br.com.alelo.consumer.consumerpat.mapper;

import br.com.alelo.consumer.consumerpat.dto.request.EstablishmentRequest;
import br.com.alelo.consumer.consumerpat.entity.Establishment;
import org.springframework.stereotype.Component;

@Component
public class EstablishmentMapper {

    public EstablishmentRequest toDto(Establishment establishment) {
        return EstablishmentRequest.builder()
                .name(establishment.getName())
                .type(establishment.getType())
                .build();
    }
}
