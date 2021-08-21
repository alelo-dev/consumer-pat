package br.com.alelo.consumer.consumerpat.dataprovider.dao;

public interface BaseDao<T> {

    T save(T entity);
}
