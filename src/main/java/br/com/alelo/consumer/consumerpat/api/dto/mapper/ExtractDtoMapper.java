package br.com.alelo.consumer.consumerpat.api.dto.mapper;

import br.com.alelo.consumer.consumerpat.api.dto.ExtractDto;
import br.com.alelo.consumer.consumerpat.domain.entity.Extract;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ExtractDtoMapper implements DtoMapper<Extract, ExtractDto> {

    @Override
    public ExtractDto toDto(Extract entity) {

        ExtractDto dto = null;

        if (entity != null) {
            dto = new ExtractDto();
            dto.setId(entity.getId());
            dto.setEstablishmentId(entity.getEstablishmentId());
            dto.setEstablishmentName(entity.getEstablishmentName());
            dto.setProductDescription(entity.getProductDescription());
            dto.setDateBuy(entity.getDateBuy());
            dto.setCardNumber(entity.getCardNumber());
            dto.setValue(entity.getValue());
        }

        return dto;
    }

    @Override
    public Extract toEntity(ExtractDto dto) {

        Extract entity = null;

        if (dto != null)  {
            entity = new Extract();
            entity.setId(dto.getId());
            entity.setEstablishmentId(dto.getEstablishmentId());
            entity.setEstablishmentName(dto.getEstablishmentName());
            entity.setProductDescription(dto.getProductDescription());
            entity.setDateBuy(dto.getDateBuy());
            entity.setCardNumber(dto.getCardNumber());
            entity.setValue(dto.getValue());
        }

        return entity;
    }

    @Override
    public List<ExtractDto> toDtos(Collection<Extract> entities) {

        List<ExtractDto> dtos = Collections.emptyList();

        if (entities != null && !entities.isEmpty()) {
            dtos = entities.stream()
                    .map(this::toDto)
                    .collect(Collectors.toList());
        }

        return dtos;
    }

    @Override
    public List<Extract> toEntities(Collection<ExtractDto> dtos) {

        List<Extract> entities = Collections.emptyList();

        if (dtos != null && !dtos.isEmpty()) {
            entities = dtos.stream()
                    .map(this::toEntity)
                    .collect(Collectors.toList());
        }

        return entities;
    }
}
