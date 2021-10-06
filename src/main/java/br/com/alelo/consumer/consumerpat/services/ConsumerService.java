package br.com.alelo.consumer.consumerpat.services;

import br.com.alelo.consumer.consumerpat.entity.Consumer;

import java.util.List;

public interface ConsumerService {

    /**
     * Busca Consumer por id
     */
    Consumer findById(Integer id);

    /**
     * Listar Consumers
     */
    List<Consumer> findAll();

    /**
     * Criar novo consumer
     */
    Consumer create(Consumer obj);

    /**
     * Atualiza Consumer
     */
    Consumer update(Integer id, Consumer obj);
}
