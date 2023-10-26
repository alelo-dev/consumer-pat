package br.com.alelo.consumer.consumerpat.application.ports.out.customer;

import br.com.alelo.consumer.consumerpat.application.core.domain.customer.Customer;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FindCustomerOutputPort {
    List<Customer> findAllCustomer(Pageable pageable);
    Optional<Customer> findCustomerById(UUID id);
}
