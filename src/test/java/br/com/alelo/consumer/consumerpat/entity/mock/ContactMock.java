package br.com.alelo.consumer.consumerpat.entity.mock;

import java.util.ArrayList;
import java.util.List;

import br.com.alelo.consumer.consumerpat.entity.Contact;
import br.com.alelo.consumer.consumerpat.enuns.ContactTypeEnum;

public class ContactMock {

	private ContactMock() {}
	
	public static Contact createCellphone() {
		return new Contact(1L, ContactTypeEnum.CELLPHONE, "62 99999-1111", null);
	}

	public static Contact createPhone() {
		return new Contact(2L, ContactTypeEnum.PHONE, "62 4004-1111", null);
	}
	
	public static Contact createEmail() {
		return new Contact(3L, ContactTypeEnum.EMAIL, "contato@email.com", null);
	}
	
	public static List<Contact> listCreate() {
		List<Contact> list = new ArrayList<Contact>();
		list.add(createCellphone());
		list.add(createPhone());
		list.add(createEmail());
		return list;
	}
}
