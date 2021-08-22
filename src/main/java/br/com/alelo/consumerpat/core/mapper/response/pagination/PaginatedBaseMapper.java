package br.com.alelo.consumerpat.core.mapper.response.pagination;

public interface PaginatedBaseMapper<T, K> {

    K convert(T object);
}
