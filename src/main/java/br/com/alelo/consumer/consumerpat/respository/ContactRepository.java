package br.com.alelo.consumer.consumerpat.respository;

import br.com.alelo.consumer.consumerpat.entity.Address;
import br.com.alelo.consumer.consumerpat.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact, Integer> {

    @Query(nativeQuery = true, value = "select * from Contact where  CONSUMER_ID = ? ")
    Optional<Contact> findByConsumerId(int consumerId);
}
