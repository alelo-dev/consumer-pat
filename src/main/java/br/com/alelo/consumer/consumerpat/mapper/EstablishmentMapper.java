package br.com.alelo.consumer.consumerpat.mapper;

import br.com.alelo.consumer.consumerpat.dto.EstablishmentDto;
import br.com.alelo.consumer.consumerpat.entity.Establishment;
import org.springframework.stereotype.Component;

/*
Poderia ser utilizado alguma lib como o MapStruct.
Como não é permitido acrescentar libs e mexer no pom fiz manualmente
 */
@Component
public class EstablishmentMapper implements Mapper<Establishment, EstablishmentDto> {

    @Override
    public Establishment mapDtoToModel(final EstablishmentDto dto) {
        return Establishment.builder()
                .establishmentName(dto.getEstablishmentName())
                .build();
    }

    @Override
    public EstablishmentDto mapModelToDto(final Establishment model) {
        return EstablishmentDto.builder()
                .id(model.getId())
                .establishmentName(model.getEstablishmentName())
                .build();
    }

    @Override
    public Establishment updateModelTarget(final Establishment target, final EstablishmentDto origin) {
        target.setEstablishmentName(origin.getEstablishmentName());

        return target;
    }
}
