package br.com.alelo.consumer.consumerpat.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alelo.consumer.consumerpat.entity.Extract;

public interface ExtractRepository extends JpaRepository<Extract, Integer> {

    List<Extract> findByCardConsumerName(String consumerName);
}
