package br.com.alelo.consumer.consumerpat.domain.services;

import br.com.alelo.consumer.consumerpat.domain.entities.Extract;
import br.com.alelo.consumer.consumerpat.domain.exceptions.ExtractNotFoundException;

import java.util.List;

public interface ExtractService {

    void save(Extract extract);

    List<Extract> findAll(int consumerId, int page, int size) throws ExtractNotFoundException;
}
