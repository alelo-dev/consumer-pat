package br.com.alelo.consumer.consumerpat.application.ports.out.cardcustomer;

import br.com.alelo.consumer.consumerpat.application.core.domain.cardcustomer.CardCustomer;

import java.util.UUID;

public interface InsertCardCustomerOutputPort {

    void insert(UUID customerId, CardCustomer cardCustomer);
}
