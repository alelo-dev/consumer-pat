package br.com.alelo.consumer.consumerpat.repository;

import br.com.alelo.consumer.consumerpat.domain.Client;
import br.com.alelo.consumer.consumerpat.domain.Establishment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstablishmentRepository extends JpaRepository<Establishment, Integer> {
    @Query(value = "FROM Establishment a ")
    Page<Establishment> findAllPaginated(final Pageable pageable);

    Optional<Establishment> findByCnpj(String cnpj);
}

