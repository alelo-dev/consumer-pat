package br.com.alelo.consumer.consumerpat.enumerator;

public enum CardEnum {

	FOODCARD(1, "Alimentação (food)"), DRUGSTORECARD(2, "Farmácia (DrugStore)"),
	FUELCARD(3, "Posto de combustivel (Fuel)");

	private Integer codigo;
	private String descricao;

	CardEnum(Integer codigo, String desc) {
		this.codigo = codigo;
		this.descricao = desc;
	}

	/**
	 * @return the codigo
	 */
	public Integer getCodigo() {
		return codigo;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	public static CardEnum getEnum(Integer codigo) {
		if (codigo == 1) {
			return CardEnum.FOODCARD;
		}
		if (codigo == 2) {
			return CardEnum.DRUGSTORECARD;
		} else {
			return CardEnum.FUELCARD;
		}
	}
}