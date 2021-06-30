package br.com.alelo.consumer.consumerpat.strategy;

import br.com.alelo.consumer.consumerpat.enumerator.CardEnum;

public class Context {

	private Card instance = null;

	public Context(CardEnum establishmentType) {
		switch (establishmentType) {
		case FOODCARD: // Alimentação
			instance = new FoodCard();
			break;

		case DRUGSTORECARD: // Farmácia (DrugStore)
			instance = new DrugstoreCard();
			break;

		default:// Posto de combustivel (Fuel)
			instance = new FuelCard();
		}
	}

	/**
	 * @return the instance
	 */
	public Card getInstance() {
		return instance;
	}

	/**
	 * @param instance the instance to set
	 */
	public void setInstance(Card instance) {
		this.instance = instance;
	}

}
