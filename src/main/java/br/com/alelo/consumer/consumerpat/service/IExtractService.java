package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.dto.RequestBuyDTO;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Extract;

import java.time.LocalDate;

/**
 * Responsible for securing extract service subscriptions
 *
 * @author mcrj
 */
public interface IExtractService {

    /**
     * Create new extract
     *
     * @param card - {@link Card}
     * @param dto - Representation of values buy
     * @param date - Date of the process
     * @return {@link Extract} - Generate and save the extract
     */
    Extract save(final Card card, final RequestBuyDTO dto, final LocalDate date);
}
