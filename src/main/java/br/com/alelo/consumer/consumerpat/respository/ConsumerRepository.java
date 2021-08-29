package br.com.alelo.consumer.consumerpat.respository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.alelo.consumer.consumerpat.domain.entity.Consumer;

public interface ConsumerRepository extends CrudRepository<Consumer, Integer>, PagingAndSortingRepository<Consumer, Integer> {

	
}
