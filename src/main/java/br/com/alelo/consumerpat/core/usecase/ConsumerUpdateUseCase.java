package br.com.alelo.consumerpat.core.usecase;

import br.com.alelo.consumerpat.core.v1.request.ConsumerUpdateV1RequestDto;
import br.com.alelo.consumerpat.core.exception.ConsumerNotFound;

public interface ConsumerUpdateUseCase {

    void update(String consumerCode, ConsumerUpdateV1RequestDto request) throws ConsumerNotFound;
}
