package br.com.alelo.consumer.consumerpat.application.core.usecase.cardcustomer;

import br.com.alelo.consumer.consumerpat.application.core.domain.cardcustomer.CardCustomer;
import br.com.alelo.consumer.consumerpat.application.ports.in.cardcustomer.InsertCardCustomerInputPort;
import br.com.alelo.consumer.consumerpat.application.ports.out.cardcustomer.InsertCardCustomerOutputPort;

import java.util.UUID;

public class InsertCardCustomerUseCase implements InsertCardCustomerInputPort {

    private final InsertCardCustomerOutputPort insertCardCustomerOutputPort;

    public InsertCardCustomerUseCase(InsertCardCustomerOutputPort insertCardCustomerOutputPort) {
        this.insertCardCustomerOutputPort = insertCardCustomerOutputPort;
    }

    @Override
    public void insert(UUID customerId, CardCustomer cardCustomer) {
        insertCardCustomerOutputPort.insert(customerId, cardCustomer);
    }
}
