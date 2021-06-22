package br.com.alelo.consumer.consumerpat.respository;

import br.com.alelo.consumer.consumerpat.data.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
