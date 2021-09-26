package br.com.alelo.consumer.consumerpat.mapper;

import br.com.alelo.consumer.consumerpat.domain.Client;
import br.com.alelo.consumer.consumerpat.domain.Establishment;
import br.com.alelo.consumer.consumerpat.dto.ClientDTO;
import br.com.alelo.consumer.consumerpat.dto.EstablishmentDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class EstablishmentMapper {

    public EstablishmentDTO toDTO(Establishment entity) {
        return EstablishmentDTO.builder()
                .cnpj(Optional.ofNullable(entity.getCnpj()).orElse(null))
                .type(Optional.ofNullable(entity.getType()).orElse(null))
                .name(Optional.ofNullable(entity.getName()).orElse(null))
                .build();
    }

    public Establishment toEntity(EstablishmentDTO dto) {
        return Establishment.builder()
                .cnpj(Optional.ofNullable(dto.getCnpj()).orElse(null))
                .type(Optional.ofNullable(dto.getType()).orElse(null))
                .name(Optional.ofNullable(dto.getName()).orElse(null))
                .build();
    }

    public List<Establishment> toEntityList(List<EstablishmentDTO> dtos) {
        return dtos.stream().map(this::toEntity).collect(Collectors.toList());
    }

    public List<EstablishmentDTO> toDTOList(List<Establishment> entityses) {
        return entityses.stream().map(this::toDTO).collect(Collectors.toList());
    }

}
