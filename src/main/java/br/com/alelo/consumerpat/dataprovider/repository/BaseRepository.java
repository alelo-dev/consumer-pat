package br.com.alelo.consumerpat.dataprovider.repository;

public interface BaseRepository<T> {

    T save(T entity);
}
