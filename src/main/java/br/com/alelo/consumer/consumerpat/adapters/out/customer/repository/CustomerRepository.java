package br.com.alelo.consumer.consumerpat.adapters.out.customer.repository;

import br.com.alelo.consumer.consumerpat.adapters.out.customer.repository.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, UUID> {

}
