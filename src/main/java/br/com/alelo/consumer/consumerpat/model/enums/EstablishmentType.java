package br.com.alelo.consumer.consumerpat.model.enums;

public enum EstablishmentType {
	
	FOODCARDNUMBER(1,"food Card Number "),
	DRUGSTORECARDNUMBER(2,"Drugstore Card Number "),
	FUELCARDNUMBER(3,"Fuel Card Number");
	
	
	private int cod;
	private String descricao;
	
	private EstablishmentType(int cod, String descrica) {
		this.cod=cod;
		this.descricao=descrica;		
		
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static EstablishmentType toEnum (Integer cod) {
		if(cod==null) {
			
			return null;
		}
		
		for (EstablishmentType x :EstablishmentType.values()) {
			if(cod.equals(x.getCod())) {
				return x;
				
			}
		}
		throw new IllegalArgumentException("id invalido: "+cod);
			
	}


}
