package br.com.alelo.consumer.consumerpat.exception;

public class CardNotAcceptedException extends RuntimeException {
private static final long serialVersionUID = 1L;
	
	public CardNotAcceptedException(String typeEstablishment, String  typeCard)  {
		super("O Cartão informado não pode ser usado para este tipo de estabelecimento! Tipo de Estabelecimento: " + typeEstablishment + ", Tipo do cartão: "+ typeCard);
	}
}
