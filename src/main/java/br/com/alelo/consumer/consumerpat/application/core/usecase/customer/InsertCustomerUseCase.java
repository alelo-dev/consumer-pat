package br.com.alelo.consumer.consumerpat.application.core.usecase.customer;

import br.com.alelo.consumer.consumerpat.application.core.domain.customer.Customer;
import br.com.alelo.consumer.consumerpat.application.ports.in.customer.InsertCustomerInputPort;
import br.com.alelo.consumer.consumerpat.application.ports.out.customer.InsertCustomerOutputPort;

public class InsertCustomerUseCase implements InsertCustomerInputPort {

    private final InsertCustomerOutputPort insertCustomerOutputPort;

    public InsertCustomerUseCase(InsertCustomerOutputPort insertCustomerOutputPort) {
        this.insertCustomerOutputPort = insertCustomerOutputPort;
    }

    @Override
    public void insert(Customer customer) {
        insertCustomerOutputPort.insert(customer);
    }
}
