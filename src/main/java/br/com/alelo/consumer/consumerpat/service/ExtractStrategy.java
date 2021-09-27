package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.dto.ExtractDTO;
import br.com.alelo.consumer.consumerpat.entity.enums.EstablishmentType;
import br.com.alelo.consumer.consumerpat.exception.BusinessSaldoException;

public interface ExtractStrategy {

    void buy(ExtractDTO dto) throws BusinessSaldoException;
    EstablishmentType getStrategyName();

}
