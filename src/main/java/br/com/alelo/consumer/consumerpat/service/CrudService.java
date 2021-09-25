package br.com.alelo.consumer.consumerpat.service;

import java.util.Optional;

import br.com.alelo.consumer.consumerpat.entity.AbstractEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class CrudService<D extends AbstractEntity, R extends JpaRepository<D, Long>> {

    public D save(final D customer) {
        return getRepository().save(customer);
    }

    public Optional<D> findById(final Long id) {
        return getRepository().findById(id);
    }

    public Page<D> listAll(final Pageable pageable) {
        return getRepository().findAll(pageable);
    }

    protected abstract R getRepository();

}
