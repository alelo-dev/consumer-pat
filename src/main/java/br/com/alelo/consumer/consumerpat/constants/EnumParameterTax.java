package br.com.alelo.consumer.consumerpat.constants;

public enum EnumParameterTax {
	
	TAX_FOOD("TAXA_CARTAO_ALIMENTACAO"),
	TAX_FUEL("TAXA_CARTAO_COMBUSTIVEL");
	
	
	private String valueName;

	EnumParameterTax(String valueName) {
        this.valueName = valueName;
    }

    public String getValueName() {
        return valueName;
    }

}
