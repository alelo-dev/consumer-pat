package br.com.alelo.consumer.consumerpat.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alelo.consumer.consumerpat.entity.Establishment;
import br.com.alelo.consumer.consumerpat.enums.EstablishmentType;

public interface EstablishmentRepository extends JpaRepository<Establishment, Integer> {

    Optional<Establishment> findByNameAndType(String name, EstablishmentType type);

}
