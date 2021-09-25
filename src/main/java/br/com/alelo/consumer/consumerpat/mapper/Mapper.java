package br.com.alelo.consumer.consumerpat.mapper;

import br.com.alelo.consumer.consumerpat.dto.CustomerDto;
import br.com.alelo.consumer.consumerpat.entity.Customer;

/**
 * @param <M> Model
 * @param <D> DTO
 */
public interface Mapper<M, D> {

    M mapDtoToModel(final D dto);

    D mapModelToDto(final M model);

    M updateModelTarget(final M target, final D origin);

}
