package br.com.alelo.consumer.consumerpat.integration.respository;

import br.com.alelo.consumer.consumerpat.domain.entity.Consumer;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ConsumerRepository extends PagingAndSortingRepository<Consumer, Long> {

    Optional<Consumer> findByConsumerCode(String consumerCode);

}
