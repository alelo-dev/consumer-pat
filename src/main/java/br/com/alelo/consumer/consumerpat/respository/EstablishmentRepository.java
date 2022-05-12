package br.com.alelo.consumer.consumerpat.respository;

import br.com.alelo.consumer.consumerpat.entity.Establishment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import utils.types.CardAndEstablishmentType;

import java.util.Optional;

public interface EstablishmentRepository extends JpaRepository<Establishment, Long> {

    @Query(nativeQuery = true, value = "select * from Establishment where NAME = ? and type = ?")
    Optional<Establishment> findEstablishmentByNameAndType(final String name, final String type);
}
