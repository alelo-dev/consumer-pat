package br.com.alelo.consumer.consumerpat.application.core.usecase.customer;

import br.com.alelo.consumer.consumerpat.application.core.domain.customer.Customer;
import br.com.alelo.consumer.consumerpat.application.ports.in.customer.UpdateCustomerInputPort;
import br.com.alelo.consumer.consumerpat.application.ports.out.customer.UpdateCustomerOutputPort;

import java.util.UUID;

public class UpdateCustomerUseCase implements UpdateCustomerInputPort {

    private final UpdateCustomerOutputPort updateCustomerOutputPort;

    public UpdateCustomerUseCase(UpdateCustomerOutputPort updateCustomerOutputPort) {
        this.updateCustomerOutputPort = updateCustomerOutputPort;
    }

    @Override
    public void update(UUID customerId, Customer customer) {
        updateCustomerOutputPort.update(customerId, customer);
    }

}
