package br.com.alelo.consumer.consumerpat.respository;

import br.com.alelo.consumer.consumerpat.domain.entity.Establishment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface EstablishmentRepository extends JpaRepository<Establishment, Integer>, Serializable {
    Establishment findById(int cardNumber);
}
