package br.com.alelo.consumer.consumerpat.business.service;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 25/07/2021 | 06:26
 */

import br.com.alelo.consumer.consumerpat.entity.Consumer;

import java.util.List;

public interface IService<T> {

    public T save(T obj);
    public T recoverById(Integer value);
    public List<T> recoverAllByConsumer(Consumer consumer);

}
