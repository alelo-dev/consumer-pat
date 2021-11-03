package br.com.alelo.consumer.consumerpat.model;

import java.util.Arrays;
import java.util.Optional;

/**
 * Enum representando ramos de negócio.
 */
public enum BusinessType {
	FOOD(1), 
	DRUGSTORE(2), 
	FUEL(3);

	private int id;

	private BusinessType(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public static Optional<BusinessType> getBusinessTypeById(int id) {
		return Arrays.stream(BusinessType.values()).filter(e -> e.getId() == id).findFirst();
	}
	
	public static void main(String[] args) {
		BusinessType[] values = BusinessType.values();
		for(BusinessType bs: values) {
			System.out.println(bs);
		}
		System.out.println(getBusinessTypeById(3));
	}
}