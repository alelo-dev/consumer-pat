package br.com.alelo.consumer.consumerpat.application.core.usecase.cardcustomer;

import br.com.alelo.consumer.consumerpat.application.core.domain.cardcustomer.CardCustomer;
import br.com.alelo.consumer.consumerpat.application.ports.in.cardcustomer.FindCardCustomerInputPort;
import br.com.alelo.consumer.consumerpat.application.ports.out.cardcustomer.FindCardCustomerOutputPort;

import java.util.Set;
import java.util.UUID;

public class FindCustomerCardUseCase implements FindCardCustomerInputPort {
    private final FindCardCustomerOutputPort findCardCustomerOutputPort;

    public FindCustomerCardUseCase(FindCardCustomerOutputPort findCardCustomerOutputPort) {
        this.findCardCustomerOutputPort = findCardCustomerOutputPort;
    }
    @Override
    public Set<CardCustomer> findCardCustomerById(UUID id) {
        return findCardCustomerOutputPort.findCardCustomerById(id);
    }
}
