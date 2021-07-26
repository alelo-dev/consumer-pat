package br.com.alelo.consumer.consumerpat.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alelo.consumer.consumerpat.model.Consumer;
import br.com.alelo.consumer.consumerpat.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    
    Optional<Contact> findOneByIdAndConsumer(Long id, Consumer consumer);

}
