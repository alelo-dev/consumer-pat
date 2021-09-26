package br.com.alelo.consumer.consumerpat.repository;

import br.com.alelo.consumer.consumerpat.domain.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    Optional<Client> findByDocumentNumber(String documentNumber);

    @Query(value = "FROM Client a ")
    Page<Client> findAllPaginated( final Pageable pageable);
}
