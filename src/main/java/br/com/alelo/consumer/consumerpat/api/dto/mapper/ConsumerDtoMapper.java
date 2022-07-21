package br.com.alelo.consumer.consumerpat.api.dto.mapper;

import br.com.alelo.consumer.consumerpat.api.dto.ConsumerDto;
import br.com.alelo.consumer.consumerpat.domain.entity.Consumer;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Faz o mapeamento entre tipos de dados de Entity e DTO facilitando
 * o intercâmbio de informações entre a camada de transporte dos dados
 * e a camada de persistência da aplicação.
 */
@Component
public class ConsumerDtoMapper implements DtoMapper<Consumer, ConsumerDto> {

    @Override
    public ConsumerDto toDto(Consumer entity) {

        ConsumerDto dto = null;

        if (entity != null) {
            dto = new ConsumerDto();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setDocumentNumber(entity.getDocumentNumber());
            dto.setBirthDate(entity.getBirthDate());
            dto.setMobilePhoneNumber(entity.getMobilePhoneNumber());
            dto.setResidencePhoneNumber(entity.getResidencePhoneNumber());
            dto.setPhoneNumber(entity.getPhoneNumber());
            dto.setEmail(entity.getEmail());
            dto.setStreet(entity.getStreet());
            dto.setNumber(entity.getNumber());
            dto.setCity(entity.getCity());
            dto.setCountry(entity.getCountry());
            dto.setPortalCode(entity.getPortalCode());
            dto.setFoodCardNumber(entity.getFoodCardNumber());
            dto.setFoodCardBalance(entity.getFoodCardBalance());
            dto.setFuelCardNumber(entity.getFuelCardNumber());
            dto.setFuelCardBalance(entity.getFuelCardBalance());
            dto.setDrugstoreCardNumber(entity.getDrugstoreCardNumber());
            dto.setDrugstoreCardBalance(entity.getDrugstoreCardBalance());
        }

        return dto;
    }

    @Override
    public Consumer toEntity(ConsumerDto dto) {

        Consumer consumer = null;

        if (dto != null) {
            consumer = new Consumer();
            consumer.setId(dto.getId());
            consumer.setName(dto.getName());
            consumer.setDocumentNumber(dto.getDocumentNumber());
            consumer.setBirthDate(dto.getBirthDate());
            consumer.setMobilePhoneNumber(dto.getMobilePhoneNumber());
            consumer.setResidencePhoneNumber(dto.getResidencePhoneNumber());
            consumer.setPhoneNumber(dto.getPhoneNumber());
            consumer.setEmail(dto.getEmail());
            consumer.setStreet(dto.getStreet());
            consumer.setNumber(dto.getNumber());
            consumer.setCity(dto.getCity());
            consumer.setCountry(dto.getCountry());
            consumer.setPortalCode(dto.getPortalCode());
            consumer.setFoodCardNumber(dto.getFoodCardNumber());
            consumer.setFoodCardBalance(dto.getFoodCardBalance());
            consumer.setFuelCardNumber(dto.getFuelCardNumber());
            consumer.setFuelCardBalance(dto.getFuelCardBalance());
            consumer.setDrugstoreCardNumber(dto.getDrugstoreCardNumber());
            consumer.setDrugstoreCardBalance(dto.getDrugstoreCardBalance());
        }

        return consumer;
    }

    @Override
    public List<ConsumerDto> toDtos(Collection<Consumer> entities) {

        List<ConsumerDto> dtos = Collections.emptyList();

        if (entities != null && !entities.isEmpty()) {
            dtos = entities.stream()
                    .map(this::toDto)
                    .collect(Collectors.toList());
        }

        return dtos;
    }

    @Override
    public List<Consumer> toEntities(Collection<ConsumerDto> dtos) {

        List<Consumer> entities = Collections.emptyList();

        if (dtos != null && !dtos.isEmpty()) {
            entities = dtos.stream()
                    .map(this::toEntity)
                    .collect(Collectors.toList());
        }

        return entities;
    }
}
