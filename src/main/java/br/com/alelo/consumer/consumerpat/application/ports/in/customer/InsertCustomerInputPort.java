package br.com.alelo.consumer.consumerpat.application.ports.in.customer;

import br.com.alelo.consumer.consumerpat.application.core.domain.customer.Customer;

public interface InsertCustomerInputPort {
    void insert(Customer customer);
}
