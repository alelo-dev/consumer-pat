package br.com.alelo.consumer.consumerpat.model.enums;

import br.com.alelo.consumer.consumerpat.model.Fee;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TypeCard implements Fee {

	FOOD("Food") {
		@Override
		public Double calcFee(Double value) {
			Double cashback = (value / 100) * DISCOUNT;
			return value - cashback;
		}
	},

	DRUGSTORE("Drugstore") {
		@Override
		public Double calcFee(Double value) {
			return value;
		}
	},
	FUEL("Fuel") {
		@Override
		public Double calcFee(Double value) {
			Double tax = (value / 100) * ADD;
			return value + tax;
		}
	};

	private String description;
	
	private static final Double DISCOUNT = 10.0;
	private static final Double ADD = 35.0;

}
