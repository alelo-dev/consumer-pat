package br.com.alelo.consumerpat.core.dataprovider.dao;

public interface BaseDao<T> {

    T save(T entity);
}
