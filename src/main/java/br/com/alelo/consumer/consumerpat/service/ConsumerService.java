package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.controller.dto.in.UpdateConsumerDTO;
import br.com.alelo.consumer.consumerpat.controller.dto.out.ResponseConsumerDTO;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ConsumerService {

    /**
     * Salva um consumidor
     * @param consumer - Consumidor
     * @return
     */
    Consumer createConsumer(final Consumer consumer);

    /**
     * Atualiza os dados do consumidor
     * @param id - ID do consumidor
     * @param consumer - DTO de atualização do consumidor
     * @return
     */
    Consumer updateConsumer(final Integer id, final UpdateConsumerDTO consumer);

    /**
     * Retorna a lista de consumidores paginada
     * @param pageable
     * @return
     */
    Page<ResponseConsumerDTO> getPageConsumer(final Pageable pageable);

}
