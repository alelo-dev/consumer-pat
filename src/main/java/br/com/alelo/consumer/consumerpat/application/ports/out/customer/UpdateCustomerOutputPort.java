package br.com.alelo.consumer.consumerpat.application.ports.out.customer;

import br.com.alelo.consumer.consumerpat.application.core.domain.customer.Customer;

import java.util.UUID;

public interface UpdateCustomerOutputPort {

    void update(UUID id, Customer customer);
}
