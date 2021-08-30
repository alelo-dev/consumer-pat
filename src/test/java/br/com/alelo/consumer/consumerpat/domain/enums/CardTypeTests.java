package br.com.alelo.consumer.consumerpat.domain.enums;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
class CardTypeTests {

	@Test
	void shouldAdjustValueRemovingCashbackFromCardTypeFOOD() {
		final var cardType = CardType.FOOD;
		final var value = BigDecimal.TEN;
		
		// Para compras no cartão de alimentação o cliente recebe um desconto de 10%
	    assertThat(cardType.adjustValue(value)).isEqualTo(new BigDecimal("9.00"));
	}
	
	@Test
	void shouldDoNothingWhenCardTypeIsDRUGSTORE() {
		final var cardType = CardType.DRUGSTORE;
		final var value = BigDecimal.TEN;
		
		assertThat(cardType.adjustValue(value)).isEqualTo(BigDecimal.TEN);
	}
	
	@Test
	void shouldAdd35PercentTaxToCardTypeFUEL() {
		final var cardType = CardType.FUEL;
		final var value = BigDecimal.TEN;
		
		// Nas compras com o cartão de combustivel existe um acrescimo de 35%
		assertThat(cardType.adjustValue(value)).isEqualTo(new BigDecimal("13.50"));
	}

}
