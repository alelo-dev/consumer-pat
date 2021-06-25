package br.com.alelo.consumer.consumerpat.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = JsonFormat.Shape.NUMBER)
public enum EstablishmentTypeEnum {
	FOOD(1, -10D),
	DRUGSTORE(2, 0D),
	FUEL(3, 35D);
	
	private EstablishmentTypeEnum(int type, double extraValuePercentage) {
		this.type = type;
		this.extraValuePercentage = extraValuePercentage;
	}
	
	static {
		establishmentTypeMap = Arrays.asList(EstablishmentTypeEnum.values()).stream().collect(Collectors.toMap(EstablishmentTypeEnum::getType, Function.identity()));
	}
	
	private int type;
	private double extraValuePercentage;
	
	private static Map<Integer, EstablishmentTypeEnum> establishmentTypeMap;
	
	@JsonValue
	public int getType() {
		return type;
	}
	
	public double getExtraValuePercentage() {
		return extraValuePercentage;
	}
	
	@JsonCreator
	public static EstablishmentTypeEnum getByType(int type) {
		return establishmentTypeMap.get(type);
	}
}
