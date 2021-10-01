package br.com.alelo.consumer.consumerpat.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum EstablishmentType {
	FOOD_ESTAB(1), DRUGSTORE_ESTAB(2), FUEL_ESTAB(3);
	
	private final int establishmentType;
	
	private EstablishmentType(int establishmentType) {
		this.establishmentType = establishmentType;
	}
	
	public int getValue() {
		return establishmentType;
	}
	
	private static Map<Integer, EstablishmentType> reverseLookup =
	        Arrays.stream(EstablishmentType.values()).collect(Collectors.toMap(EstablishmentType::getValue, Function.identity()));
	
	public static EstablishmentType fromInt(final int id) {
        return reverseLookup.getOrDefault(id, null);
    }
}
