package br.com.alelo.consumer.consumerpat.services;

import br.com.alelo.consumer.consumerpat.entity.Extract;

public interface ExtractService {

    void save(Extract extract);

    Extract findByConsumerName(String name);

    Extract findOrFail(Long id);
}
