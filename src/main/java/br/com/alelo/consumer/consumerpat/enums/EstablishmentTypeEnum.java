package br.com.alelo.consumer.consumerpat.enums;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = JsonFormat.Shape.NUMBER)
public enum EstablishmentTypeEnum {
	FOOD(1, -10D),
	DRUGSTORE(2, 0D),
	FUEL(3, 35D);
	
	EstablishmentTypeEnum(int type, double extraValuePercentage) {
		this.type = type;
		this.extraValuePercentage = extraValuePercentage;
	}
	
	int type;
	double extraValuePercentage;
	
	@JsonValue
	public int getType() {
		return type;
	}
	
	public double getExtraValuePercentage() {
		return extraValuePercentage;
	}
	
	@JsonCreator
    public static EstablishmentTypeEnum forValue(int value) {
		return Arrays.asList(EstablishmentTypeEnum.values()).stream().filter(e -> e.type == value).findFirst().orElse(null);
    }
}
