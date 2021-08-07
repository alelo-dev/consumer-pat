package br.com.alelo.consumer.consumerpat.enuns;

import java.util.Arrays;

import br.com.alelo.consumer.consumerpat.processor.DrugstoreBuyProcessor;
import br.com.alelo.consumer.consumerpat.processor.FoodBuyProcessor;
import br.com.alelo.consumer.consumerpat.processor.FuelBuyProcessor;
import br.com.alelo.consumer.consumerpat.processor.IBuyProcessor;
import lombok.Getter;

@Getter
public enum EstablishmentTypeEnum {
	
	FOOD(1, new FoodBuyProcessor()),
	DRUGSTORE(2, new DrugstoreBuyProcessor()),
	FUEL(3, new FuelBuyProcessor());
	
	EstablishmentTypeEnum(Integer code, IBuyProcessor processor) {
		this.code = code;
		this.processor = processor;
	}
	
	private Integer code;
	private IBuyProcessor processor;
	
	public EstablishmentTypeEnum to(Integer code) {
		return Arrays.stream(EstablishmentTypeEnum.values())
				.filter( type -> type.code.equals(code))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException(String.format("Unsupported value: %s", code)));
	}
}
