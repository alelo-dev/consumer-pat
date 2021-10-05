package br.com.alelo.consumer.consumerpat.domain.exception.not_found;

import br.com.alelo.consumer.consumerpat.domain.exception.EntityNotFoundException;

public class ConsumerNotFoundException extends EntityNotFoundException{

	private static final long serialVersionUID = 1L;
	private static final String NAO_EXISTE_NA_BASE_DE_DADOS = "Consumidor de código %d não existe na base de dados!";

	public ConsumerNotFoundException(String msg) {
		super(msg);
	}
	
	public ConsumerNotFoundException(Long id) {
		this(String.format(NAO_EXISTE_NA_BASE_DE_DADOS, id));
	}
	
}
