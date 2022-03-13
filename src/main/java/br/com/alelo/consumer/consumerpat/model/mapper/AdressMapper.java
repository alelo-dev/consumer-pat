package br.com.alelo.consumer.consumerpat.model.mapper;

import org.mapstruct.Mapper;

import br.com.alelo.consumer.consumerpat.model.Adress;
import br.com.alelo.consumer.consumerpat.model.dto.AdressDTO;

@Mapper(componentModel = "spring")
public interface AdressMapper {

	Adress toModel(AdressDTO vo);
}
