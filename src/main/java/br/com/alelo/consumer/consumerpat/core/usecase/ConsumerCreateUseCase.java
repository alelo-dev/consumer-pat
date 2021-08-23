package br.com.alelo.consumer.consumerpat.core.usecase;

import br.com.alelo.consumer.consumerpat.core.exception.BadRequestException;
import br.com.alelo.consumer.consumerpat.core.dto.v1.request.ConsumerCreateV1RequestDto;

public interface ConsumerCreateUseCase {

    String create(ConsumerCreateV1RequestDto request) throws BadRequestException;
}
