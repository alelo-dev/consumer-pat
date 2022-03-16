package br.com.alelo.consumer.consumerpat.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alelo.consumer.consumerpat.entity.Contact;
import br.com.alelo.consumer.consumerpat.enums.ContactType;

public interface ContactRepository extends JpaRepository<Contact, Integer> {

    List<Contact> findByConsumerIdAndType(Integer idConsumer, ContactType type);

    List<Contact> findByConsumerName(String consumerName);
}
