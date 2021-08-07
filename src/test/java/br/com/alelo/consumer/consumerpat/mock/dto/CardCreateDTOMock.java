package br.com.alelo.consumer.consumerpat.mock.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.alelo.consumer.consumerpat.dto.CardCreateDTO;
import br.com.alelo.consumer.consumerpat.enuns.CardTypeEnum;

public class CardCreateDTOMock {

	private CardCreateDTOMock() {}
	
	public static CardCreateDTO createFood() {
		return CardCreateDTO.builder()
				.number("1111222233334444")
				.type(CardTypeEnum.FOOD)
				.balance(BigDecimal.valueOf(300))
				.build();
	}
	
	public static CardCreateDTO createDrugstore() {
		return CardCreateDTO.builder()
				.number("0000111100001111")
				.type(CardTypeEnum.DRUGSTORE)
				.balance(BigDecimal.valueOf(200))
				.build();
	}
	
	public static CardCreateDTO createFuel() {
		return CardCreateDTO.builder()
				.number("9999000099990000")
				.type(CardTypeEnum.FUEL)
				.balance(BigDecimal.valueOf(100))
				.build();
	}
	
	public static List<CardCreateDTO> list() {
		List<CardCreateDTO> list = new ArrayList<CardCreateDTO>();
		list.add(createFood());
		list.add(createDrugstore());
		list.add(createFuel());
		return list;
	}
}
