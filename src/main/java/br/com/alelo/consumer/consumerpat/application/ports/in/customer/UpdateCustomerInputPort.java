package br.com.alelo.consumer.consumerpat.application.ports.in.customer;

import br.com.alelo.consumer.consumerpat.application.core.domain.customer.Customer;

import java.util.UUID;

public interface UpdateCustomerInputPort {
    void update(UUID customerId, Customer customer);
}
