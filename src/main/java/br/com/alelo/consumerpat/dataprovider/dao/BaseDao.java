package br.com.alelo.consumerpat.dataprovider.dao;

public interface BaseDao<T> {

    T save(T entity);
}
