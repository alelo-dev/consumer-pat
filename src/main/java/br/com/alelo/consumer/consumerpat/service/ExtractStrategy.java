package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.dto.ExtractDTO;
import br.com.alelo.consumer.consumerpat.entity.enums.CompanyType;
import br.com.alelo.consumer.consumerpat.exception.BusinessSaldoException;

public interface ExtractStrategy {

    void buy(ExtractDTO dto) throws BusinessSaldoException;
    CompanyType getStrategyName();

}
