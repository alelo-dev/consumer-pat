package br.com.alelo.consumer.consumerpat.dataprovider.jpa.repository;

import br.com.alelo.consumer.consumerpat.dataprovider.jpa.entity.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactJpaRepository extends JpaRepository<ContactEntity, Long> {
}
