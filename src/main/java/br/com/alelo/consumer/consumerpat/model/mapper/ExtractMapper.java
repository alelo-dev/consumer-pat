package br.com.alelo.consumer.consumerpat.model.mapper;

import org.mapstruct.Mapper;

import br.com.alelo.consumer.consumerpat.model.Extract;
import br.com.alelo.consumer.consumerpat.model.dto.ExtractDTO;

@Mapper(componentModel = "spring")
public interface ExtractMapper {

	ExtractDTO toDTO(Extract extract);
}
