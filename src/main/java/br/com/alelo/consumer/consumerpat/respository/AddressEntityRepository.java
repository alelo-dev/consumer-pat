package br.com.alelo.consumer.consumerpat.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.alelo.consumer.consumerpat.entity.AddressEntity;

interface AddressEntityRepository extends JpaRepository<AddressEntity, Integer>{
    
}
