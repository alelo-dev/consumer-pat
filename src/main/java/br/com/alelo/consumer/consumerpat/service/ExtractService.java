package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.dto.BuyDTO;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Extract;

import java.time.LocalDate;

public interface ExtractService {

    Extract save(final Card card, final BuyDTO dto, final LocalDate date);
}
