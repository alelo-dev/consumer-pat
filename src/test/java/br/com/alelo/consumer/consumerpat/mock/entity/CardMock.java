package br.com.alelo.consumer.consumerpat.mock.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.enuns.CardTypeEnum;

public class CardMock {

	private CardMock() {}
	
	public static Card createFood() {
		return new Card(1L, "1111222233334444", CardTypeEnum.FOOD, BigDecimal.valueOf(300), null);
	}
	
	public static Card createDrugstore() {
		return new Card(2L, "0000111100001111", CardTypeEnum.DRUGSTORE, BigDecimal.valueOf(200), null);
	}
	
	public static Card createFuel() {
		return new Card(3L, "9999000099990000", CardTypeEnum.FUEL, BigDecimal.valueOf(100), null);
	}
	
	public static List<Card> listCreate() {
		List<Card> list = new ArrayList<Card>();
		list.add(createFood());
		list.add(createDrugstore());
		list.add(createFuel());
		return list;
	}
}
