package br.com.alelo.consumer.consumerpat.application.ports.in.cardcustomer;

import br.com.alelo.consumer.consumerpat.application.core.domain.cardcustomer.CardCustomer;

import java.util.UUID;

public interface InsertCardCustomerInputPort {
    void insert(UUID customerId, CardCustomer cardCustomer);
}
