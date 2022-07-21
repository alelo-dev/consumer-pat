package br.com.alelo.consumer.consumerpat.api.dto.validation;

import br.com.alelo.consumer.consumerpat.api.dto.Dto;
import br.com.alelo.consumer.consumerpat.domain.exception.InvalidRequestException;

public interface DtoValidator<T extends Dto> {
    void validate(T dto) throws InvalidRequestException;
}
