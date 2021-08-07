package br.com.alelo.consumer.consumerpat.mock.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.alelo.consumer.consumerpat.dto.ContactCreateDTO;
import br.com.alelo.consumer.consumerpat.enuns.ContactTypeEnum;

public class ContactCreateDTOMock {

	private ContactCreateDTOMock() {}
	
	public static ContactCreateDTO createEmail() {
		return ContactCreateDTO.builder()
				.type(ContactTypeEnum.EMAIL)
				.value("contato@email.com")
				.build();
	}
	
	public static ContactCreateDTO createPhone() {
		return ContactCreateDTO.builder()
				.type(ContactTypeEnum.PHONE)
				.value("62 4004-0001")
				.build();
	}
	
	public static ContactCreateDTO createCellphone() {
		return ContactCreateDTO.builder()
				.type(ContactTypeEnum.CELLPHONE)
				.value("62 99999-1111")
				.build();
	}
	
	
	public static List<ContactCreateDTO> list() {
		List<ContactCreateDTO> list = new ArrayList<ContactCreateDTO>();
		list.add(createEmail());
		list.add(createPhone());
		list.add(createCellphone());
		return list;
	}
}
