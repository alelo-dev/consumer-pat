package br.com.alelo.consumer.consumerpat.mock.entity;

import java.util.ArrayList;
import java.util.List;

import br.com.alelo.consumer.consumerpat.entity.Address;
import br.com.alelo.consumer.consumerpat.enuns.AddressTypeEnum;

public class AddressMock {

	private AddressMock() {}
	
	public static Address create() {
		return new Address(1L, AddressTypeEnum.HOME, "Rua 1", 100, "Centro", "Goiânia", "Brasil", "74.490-300", null);
	}
	
	public static List<Address> listCreate() {
		List<Address> list = new ArrayList<Address>();
		list.add(create());
		return list;
	}
}
