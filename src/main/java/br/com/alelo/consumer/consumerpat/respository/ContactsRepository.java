package br.com.alelo.consumer.consumerpat.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alelo.consumer.consumerpat.model.entity.Contacts;

public interface ContactsRepository extends JpaRepository<Contacts, Integer> {

   
}
