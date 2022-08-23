package br.com.alelo.consumer.consumerpat.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alelo.consumer.consumerpat.model.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Integer> {
}
