package br.com.alelo.consumer.consumerpat.exception;

public class CardNotFoundException extends RuntimeException {
private static final long serialVersionUID = 1L;
	
	public CardNotFoundException(String cardNumber)  {
		super("O Cartão informado não foi encontrado! cardNumber: " + cardNumber);
	}
}
