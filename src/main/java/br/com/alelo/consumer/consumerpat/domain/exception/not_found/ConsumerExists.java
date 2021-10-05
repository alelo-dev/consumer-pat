package br.com.alelo.consumer.consumerpat.domain.exception.not_found;

import br.com.alelo.consumer.consumerpat.domain.exception.EntityNotFoundException;

public class ConsumerExists extends EntityNotFoundException{

	private static final long serialVersionUID = 1L;
	private static final String EXISTE_NA_BASE_DE_DADOS = "O documento %s já existe na base de dados!";

	public ConsumerExists(String msg) {
		super(msg);
	}
	
	public ConsumerExists(String msg, String document) {
		super(String.format(EXISTE_NA_BASE_DE_DADOS, document));
	}
	
}
