package br.com.alelo.consumer.consumerpat.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.alelo.consumer.consumerpat.entity.ContactEntity;

interface ContactEntityRepository extends JpaRepository<ContactEntity, Integer>{
    
}
