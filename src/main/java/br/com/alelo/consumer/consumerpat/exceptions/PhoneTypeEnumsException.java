package br.com.alelo.consumer.consumerpat.exceptions;

public class PhoneTypeEnumsException extends BussinesException {

	private static final long serialVersionUID = 1L;

	public PhoneTypeEnumsException() {

		super("tipo de telefone inválido");
	}
}
