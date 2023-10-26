package br.com.alelo.consumer.consumerpat.application.ports.out.customer;

import br.com.alelo.consumer.consumerpat.application.core.domain.customer.Customer;

public interface InsertCustomerOutputPort {
    void insert(Customer customer);
}
