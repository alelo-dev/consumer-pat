package br.com.alelo.consumerpat.core.usecase;

import br.com.alelo.consumerpat.core.v1.request.ConsumerCreateV1RequestDto;
import br.com.alelo.consumerpat.core.exception.BadRequestException;

public interface ConsumerCreateUseCase {

    String create(ConsumerCreateV1RequestDto request) throws BadRequestException;
}
