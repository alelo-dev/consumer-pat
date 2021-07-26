package br.com.alelo.consumer.consumerpat.respository;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 25/07/2021 | 06:31
 */

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface IRepository<T> {

    List<T> findAllByConsumer(Consumer consumer);
    T save(T obj);
    Optional<T> findById(Integer value);

}
