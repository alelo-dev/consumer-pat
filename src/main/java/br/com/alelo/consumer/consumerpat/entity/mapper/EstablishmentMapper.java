package br.com.alelo.consumer.consumerpat.entity.mapper;

import br.com.alelo.consumer.consumerpat.entity.Establishment;
import br.com.alelo.consumer.consumerpat.entity.dto.EstablishmentDTO;
import org.springframework.beans.BeanUtils;

public class EstablishmentMapper {

    private EstablishmentMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static Establishment dtoToEntity(EstablishmentDTO dto){
        var entity = new Establishment();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }


    public static EstablishmentDTO entityToDTO(Establishment entity){
        var dto = EstablishmentDTO.builder().build();
        BeanUtils.copyProperties(dto, entity);
        return dto;
    }

}
