package br.com.alelo.consumer.consumerpat.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alelo.consumer.consumerpat.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

    List<Address> findByConsumerId(Integer idConsumer);
    
    List<Address> findByConsumerName(String consumerName);
}
