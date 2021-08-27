package br.com.alelo.consumer.consumerpat.domain.enums;

import java.math.BigDecimal;

public enum CardType {
	
	FOOD {
		// Para compras no cartão de alimentação o cliente recebe um desconto de 10%
		@Override
		public BigDecimal adjustValue(BigDecimal value) {
			final var cashback  = value.multiply(new BigDecimal("0.1")).setScale(2);
			return value.subtract(cashback);
		}
	},
	DRUGSTORE,
	FUEL {
		// Nas compras com o cartão de combustivel existe um acrescimo de 35%
		@Override
		public BigDecimal adjustValue(BigDecimal value) {
			return value.multiply(new BigDecimal("1.35")).setScale(2);
		}
	};
	
	public BigDecimal adjustValue(BigDecimal value) { return value; }

}
