package br.com.alelo.consumer.consumerpat.domain.repositories;

import br.com.alelo.consumer.consumerpat.domain.entities.Extract;

import java.util.List;

public interface ExtractRepository {

    void save(Extract extract);

    List<Extract> findAll(int consumerId, int page, int size);
}
