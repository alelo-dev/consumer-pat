package br.com.alelo.consumer.consumerpat.business;

import br.com.alelo.consumer.consumerpat.model.dto.DtoBuy;
import br.com.alelo.consumer.consumerpat.utils.exception.ProcessException;

public interface CardBusiness {

    void setBalance(long cardNumber, double value);

    void buy(DtoBuy dtoBuy) throws ProcessException;
  }
