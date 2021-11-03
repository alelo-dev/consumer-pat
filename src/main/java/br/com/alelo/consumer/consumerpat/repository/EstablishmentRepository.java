package br.com.alelo.consumer.consumerpat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alelo.consumer.consumerpat.model.BusinessType;
import br.com.alelo.consumer.consumerpat.model.Establishment;

public interface EstablishmentRepository extends JpaRepository<Establishment, Long> {
	// TODO findByCNPJ
	List<Establishment> findByNameAndType(String name, BusinessType type);
}
