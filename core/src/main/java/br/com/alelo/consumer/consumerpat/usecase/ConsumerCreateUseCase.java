package br.com.alelo.consumer.consumerpat.usecase;

import br.com.alelo.consumer.consumerpat.dto.v1.request.ConsumerCreateV1RequestDto;
import br.com.alelo.consumer.consumerpat.exception.BadRequestException;

public interface ConsumerCreateUseCase {

    String create(ConsumerCreateV1RequestDto request) throws BadRequestException;
}
