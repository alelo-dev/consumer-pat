package br.com.alelo.consumer.consumerpat.dataprovider.repository;

public interface BaseRepository<T> {

    T save(T entity);
}
