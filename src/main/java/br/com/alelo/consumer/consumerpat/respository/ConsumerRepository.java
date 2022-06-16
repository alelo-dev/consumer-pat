package br.com.alelo.consumer.consumerpat.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.alelo.consumer.consumerpat.entity.Consumer;

public interface ConsumerRepository extends JpaRepository<Consumer, Integer> {

	static final String CONSULTAR_TODOS = "select c from Consumer c";

	@Query(CONSULTAR_TODOS)
	List<Consumer> getAllConsumersList();
}
