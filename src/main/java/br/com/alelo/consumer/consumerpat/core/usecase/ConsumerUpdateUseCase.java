package br.com.alelo.consumer.consumerpat.core.usecase;

import br.com.alelo.consumer.consumerpat.core.exception.ConsumerNotFound;
import br.com.alelo.consumer.consumerpat.core.dto.v1.request.ConsumerUpdateV1RequestDto;

public interface ConsumerUpdateUseCase {

    void update(String consumerCode, ConsumerUpdateV1RequestDto request) throws ConsumerNotFound;
}
