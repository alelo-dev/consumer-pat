package br.com.alelo.consumer.consumerpat.respository;

import br.com.alelo.consumer.consumerpat.entity.Establishment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EstablishmentRepository extends JpaRepository<Establishment, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM ESTABLISHMENT")
    List<Establishment> getAllEstablishmentList();
}
