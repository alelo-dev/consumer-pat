package br.com.alelo.consumer.consumerpat.application.port.out;

import br.com.alelo.consumer.consumerpat.domain.Extract;

public interface SaveExtractPort {

  public Extract save(final Extract extract);
}
