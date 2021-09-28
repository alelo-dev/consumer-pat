package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.domain.dto.v2.BuyDTO;
import br.com.alelo.consumer.consumerpat.domain.entity.ExtractEntity;

public interface ExtractService {

    ExtractEntity save(BuyDTO buy);
}
