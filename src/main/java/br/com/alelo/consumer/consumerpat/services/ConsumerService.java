package br.com.alelo.consumer.consumerpat.services;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import org.springframework.data.domain.Page;

public interface ConsumerService {

    /**
     * Busca Consumer por id
     */
    Consumer findById(Integer id);

    /**
     * Lista os consumers com paginação
     */
    Page<Consumer> search(Integer page, Integer linesPerPage, String orderBy, String direction);

    /**
     * Criar novo consumer
     */
    Consumer create(Consumer obj);

    /**
     * Atualiza Consumer
     */
    Consumer update(Integer id, Consumer obj);
 }
