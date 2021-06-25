package br.com.alelo.consumer.consumerpat.model.enums;

public enum AddressType {
	
	HOMEADDRESS(1,"Home Address"),
	BUSINESSADDRESS(2,"Business Address"),
	OTHERADDRESS(3,"Other Address");
	
	
	private int cod;
	private String descricao;
	
	private AddressType(int cod, String descrica) {
		this.cod=cod;
		this.descricao=descrica;		
		
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static AddressType toEnum (Integer cod) {
		if(cod==null) {
			
			return null;
		}
		
		for (AddressType x :AddressType.values()) {
			if(cod.equals(x.getCod())) {
				return x;
				
			}
		}
		throw new IllegalArgumentException("id invalido: "+cod);
			
	}


}
