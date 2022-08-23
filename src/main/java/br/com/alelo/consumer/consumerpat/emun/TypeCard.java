package br.com.alelo.consumer.consumerpat.emun;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TypeCard {

	 FOOD(1) {
		@Override
		public double calculateBuy(double value) {
			return value - value * 0.1;
		
		}
	}, DRUG(2) {
		@Override
		public double calculateBuy(double value) {			
			return value;
		}
	}, FUEL(3) {
		@Override
		public double calculateBuy(double value) {
			return value + value * 0.35;
		}
	};
	 
	 private Integer value;
	 
	 public abstract double calculateBuy(double value);
}
