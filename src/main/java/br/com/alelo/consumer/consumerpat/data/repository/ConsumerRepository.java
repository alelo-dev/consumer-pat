package br.com.alelo.consumer.consumerpat.data.repository;

import br.com.alelo.consumer.consumerpat.data.entity.Consumer;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ConsumerRepository extends PagingAndSortingRepository<Consumer, Integer> {
}
