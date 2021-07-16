package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.BaseEntity;
import br.com.alelo.consumer.consumerpat.respository.BaseRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@Getter
@AllArgsConstructor
public class BaseServiceImpl<T extends BaseEntity, R extends BaseRepository> implements BaseService<T> {

    private final R repository;

    @Override
    public Page<T> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Optional<T> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public T update(Long id, T entity) {
        return (T) repository.save(entity);
    }

    @Override
    public T save(T entity) {
        return (T) repository.save(entity);
    }
}
