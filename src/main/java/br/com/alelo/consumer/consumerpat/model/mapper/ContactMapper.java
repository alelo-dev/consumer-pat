package br.com.alelo.consumer.consumerpat.model.mapper;

import org.mapstruct.Mapper;

import br.com.alelo.consumer.consumerpat.model.Contact;
import br.com.alelo.consumer.consumerpat.model.dto.ContactVO;

@Mapper(componentModel = "spring")
public interface ContactMapper {

	Contact voToModel(ContactVO vo);
}
