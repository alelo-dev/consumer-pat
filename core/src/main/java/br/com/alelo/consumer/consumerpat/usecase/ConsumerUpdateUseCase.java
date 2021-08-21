package br.com.alelo.consumer.consumerpat.usecase;

import br.com.alelo.consumer.consumerpat.dto.v1.request.ConsumerUpdateV1RequestDto;
import br.com.alelo.consumer.consumerpat.exception.ConsumerNotFound;

public interface ConsumerUpdateUseCase {

    void update(String consumerCode, ConsumerUpdateV1RequestDto request) throws ConsumerNotFound;
}
