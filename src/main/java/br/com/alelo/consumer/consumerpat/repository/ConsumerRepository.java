package br.com.alelo.consumer.consumerpat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alelo.consumer.consumerpat.model.Consumer;

public interface ConsumerRepository extends JpaRepository<Consumer, Long> {

    Consumer findByCpf(String cpf);

}