package br.com.alelo.consumer.consumerpat.model.repository;

import br.com.alelo.consumer.consumerpat.model.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
