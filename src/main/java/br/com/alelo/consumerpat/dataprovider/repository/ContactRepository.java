package br.com.alelo.consumerpat.dataprovider.repository;

import br.com.alelo.consumerpat.core.dataprovider.entity.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<ContactEntity, Long> {
}
