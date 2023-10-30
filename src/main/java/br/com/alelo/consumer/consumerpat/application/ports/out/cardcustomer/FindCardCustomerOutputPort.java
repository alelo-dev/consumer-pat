package br.com.alelo.consumer.consumerpat.application.ports.out.cardcustomer;

import br.com.alelo.consumer.consumerpat.application.core.domain.cardcustomer.CardCustomer;

import java.util.Set;
import java.util.UUID;

public interface FindCardCustomerOutputPort {
    Set<CardCustomer> findCardCustomerById(UUID id);
}
