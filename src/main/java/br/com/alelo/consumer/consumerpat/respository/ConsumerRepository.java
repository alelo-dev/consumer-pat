package br.com.alelo.consumer.consumerpat.respository;

import br.com.alelo.consumer.consumerpat.entity.Consumer;

public interface ConsumerRepository extends BaseRepository<Consumer> {

    Consumer findByDocumentNumber(int documentNumber);
}
