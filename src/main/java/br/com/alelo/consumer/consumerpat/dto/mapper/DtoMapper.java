package br.com.alelo.consumer.consumerpat.dto.mapper;

import java.util.Collection;
import java.util.List;

/**
 * Interface responsável por definir mapeamentos entre uma Entity da camada
 * de persistência e um DTO da camada de transporte.
 *
 * @param <E> - tipo de dados das entidades
 * @param <D> - tipo de dados dos DTOs
 */
public interface DtoMapper<E, D> {
    D toDto(E entity);

    E toEntity(D dto);

    List<D> toDtos(Collection<E> entities);

    List<E> toEntities(Collection<D> dtos);
}
