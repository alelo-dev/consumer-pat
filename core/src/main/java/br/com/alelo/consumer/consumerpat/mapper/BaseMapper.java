package br.com.alelo.consumer.consumerpat.mapper;

public interface BaseMapper<T, K> {

    K convert(T object);
}
