package br.com.alelo.consumer.consumerpat.domain.service;

import br.com.alelo.consumer.consumerpat.domain.entity.Extract;
import br.com.alelo.consumer.consumerpat.domain.exception.ConsumerApplicationException;

/**
 * Serviços relativos ao extrato das transações do cliente.
 */
public interface ExtractService {

    Extract saveExtract(Extract extract) throws ConsumerApplicationException;
}
