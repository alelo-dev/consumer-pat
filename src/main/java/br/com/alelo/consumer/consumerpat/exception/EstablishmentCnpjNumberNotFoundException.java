package br.com.alelo.consumer.consumerpat.exception;

public class EstablishmentCnpjNumberNotFoundException extends EntityNotFoundException {


	public EstablishmentCnpjNumberNotFoundException(String number) {
		super(String.format("Não existe um estabelecimento de CNPJ: %s", number));
	}
	
}
