package br.com.alelo.consumer.consumerpat.domain.mapper;

import br.com.alelo.consumer.consumerpat.domain.dto.EstablishmentDTO;
import br.com.alelo.consumer.consumerpat.domain.entity.Establishment;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class EstablishmentMapper {


    public Establishment dtoToEntity(EstablishmentDTO dto){
        var entity = new Establishment();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }


    public EstablishmentDTO entityToDTO(Establishment entity){
        var dto = EstablishmentDTO.builder().build();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

}
