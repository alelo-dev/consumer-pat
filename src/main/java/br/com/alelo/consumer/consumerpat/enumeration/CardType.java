package br.com.alelo.consumer.consumerpat.enumeration;

import lombok.Getter;

@Getter
public enum CardType {
   
	NONE		(0), 
	FOOD		(1), 
    DRUGSTORE	(2),
    FUEL		(3);
	
	private CardType(Integer id) {
		this.id = id;
	}

	private final Integer id;
	
	public static CardType getById(Integer id){
		for (CardType cardType : CardType.values()) {
			if (cardType.getId().equals(id)) {
				return cardType;
			}
		}
		return null;
	}
	
}