package br.com.alelo.consumer.consumerpat.application.port.in;

import br.com.alelo.consumer.consumerpat.domain.Extract;
import br.com.alelo.consumer.consumerpat.domain.PurchaseOccurrence;

public interface PurchaseRegister {
  
  public Extract register(final PurchaseOccurrence purchaseOccurrence);

}
