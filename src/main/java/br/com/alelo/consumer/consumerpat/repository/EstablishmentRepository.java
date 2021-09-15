package br.com.alelo.consumer.consumerpat.repository;

import br.com.alelo.consumer.consumerpat.entity.Establishment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EstablishmentRepository extends JpaRepository<Establishment, Integer> {

    @Query(nativeQuery = true, value = "select * from Establishment where TYPE = :type and NAME = :name ")
    Optional<Establishment> findByTypeAndName(@Param("type") String type, @Param("name") String name);
}
