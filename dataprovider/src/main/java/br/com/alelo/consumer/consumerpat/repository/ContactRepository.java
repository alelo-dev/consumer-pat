package br.com.alelo.consumer.consumerpat.repository;

import br.com.alelo.consumer.consumerpat.dataprovider.entity.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<ContactEntity, Long> {
}
