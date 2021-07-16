package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BaseService<T extends BaseEntity> {

    Page<T> findAll(Pageable pageable);

    Optional<T> findById(Long id);

    void delete(Long id);

    T update(Long id, T entity);

    T save(T entity);
}
