package br.com.alelo.consumer.consumerpat.application.core.usecase.customer;

import br.com.alelo.consumer.consumerpat.application.core.domain.customer.Customer;
import br.com.alelo.consumer.consumerpat.application.ports.in.customer.FindCustomerInputPort;
import br.com.alelo.consumer.consumerpat.application.ports.out.customer.FindCustomerOutputPort;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class FindCustomerUseCase implements FindCustomerInputPort {
    private final FindCustomerOutputPort findCustomerOutputPort;

    public FindCustomerUseCase(FindCustomerOutputPort findCustomerOutputPort) {
        this.findCustomerOutputPort = findCustomerOutputPort;
    }
    @Override
    public List<Customer> findAllCustomers(Pageable pageable) {
        return findCustomerOutputPort.findAllCustomer(pageable);
    }
    @Override
    public Optional<Customer> findCustomerById(UUID id) {
        return findCustomerOutputPort.findCustomerById(id);
    }
}
