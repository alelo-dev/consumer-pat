package br.com.alelo.consumer.consumerpat.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alelo.consumer.consumerpat.model.Address;
import br.com.alelo.consumer.consumerpat.model.Consumer;

public interface AddressRepository extends JpaRepository<Address, Long> {
    
    Optional<Address> findByIdAndConsumer(Long id, Consumer consumer);
    
}
