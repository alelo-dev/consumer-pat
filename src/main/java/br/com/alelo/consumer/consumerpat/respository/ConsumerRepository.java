package br.com.alelo.consumer.consumerpat.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.alelo.consumer.consumerpat.model.entity.Consumer;

import java.util.List;

public interface ConsumerRepository extends JpaRepository<Consumer, Integer> {

}
