package br.com.alelo.consumer.consumerpat.domain.mapper;

import br.com.alelo.consumer.consumerpat.domain.dto.EstablishmentDTO;
import br.com.alelo.consumer.consumerpat.domain.entity.Establishment;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstablishmentMapper implements Serializable {

    private static final long serialVersionUID = 2405172041950251807L;

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

    public List<EstablishmentDTO> listEntityToDTO(List<Establishment> entityList) {
        return entityList.stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
    }

    public List<Establishment> listDTOToEntity(List<EstablishmentDTO> dtoList) {
        return dtoList.stream()
                .map(this::dtoToEntity)
                .collect(Collectors.toList());
    }

}
