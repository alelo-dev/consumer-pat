package br.com.alelo.consumer.consumerpat.respository;

import br.com.alelo.consumer.consumerpat.data.model.Consumer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ConsumerRepository extends PagingAndSortingRepository<Consumer, Integer> {

}
