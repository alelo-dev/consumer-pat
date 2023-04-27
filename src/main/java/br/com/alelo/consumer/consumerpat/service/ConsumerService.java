package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class ConsumerService {

    ConsumerRepository repository;

    public ConsumerService(ConsumerRepository repository) {
        this.repository = repository;
    }

    public void insert(Consumer consumer) {
        repository.save(consumer);
    }

    public void update(Consumer consumer) {
        repository.findById(consumer.getId())
                .filter(consumer1 -> compareBalances(consumer1,consumer))
                .map(consumer2 -> repository.save(consumer))
                .orElseThrow(IllegalArgumentException::new);
    }

    private boolean compareBalances(Consumer consumer1, Consumer consumer2) {
        return Objects.equals(consumer1.getDrugstoreCardBalance(),consumer2.getDrugstoreCardBalance())
                && Objects.equals(consumer1.getFoodCardBalance(),consumer2.getFoodCardBalance())
                && Objects.equals(consumer1.getFuelCardBalance(),consumer2.getFuelCardBalance());
    }

    public Page<Consumer> findAll(Pageable pageable){
        return repository.findAll(pageable);
    }
}
