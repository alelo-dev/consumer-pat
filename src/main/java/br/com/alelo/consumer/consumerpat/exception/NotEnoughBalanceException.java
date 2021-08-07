package br.com.alelo.consumer.consumerpat.exception;

import java.math.BigDecimal;

public class NotEnoughBalanceException extends RuntimeException {
private static final long serialVersionUID = 1L;
	
	public NotEnoughBalanceException(BigDecimal balance)  {
		super("Saldo insuficiente para completar a compra! Saldo disponível: R$ " + balance);
	}
}
