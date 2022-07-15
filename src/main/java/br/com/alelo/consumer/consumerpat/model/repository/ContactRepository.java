package br.com.alelo.consumer.consumerpat.model.repository;

import br.com.alelo.consumer.consumerpat.model.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
