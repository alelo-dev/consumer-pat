package br.com.alelo.consumer.consumerpat.respository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alelo.consumer.consumerpat.entity.Extract;
  
public interface ExtractJPA extends JpaRepository<Extract, Integer> {
}
