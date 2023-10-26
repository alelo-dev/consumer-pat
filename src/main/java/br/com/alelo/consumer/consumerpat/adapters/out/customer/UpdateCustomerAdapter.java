package br.com.alelo.consumer.consumerpat.adapters.out.customer;

import br.com.alelo.consumer.consumerpat.adapters.out.customer.repository.CustomerRepository;
import br.com.alelo.consumer.consumerpat.application.core.domain.customer.Customer;
import br.com.alelo.consumer.consumerpat.application.ports.out.customer.UpdateCustomerOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UpdateCustomerAdapter implements UpdateCustomerOutputPort {

    private final CustomerRepository customerRepository;

    @Override
    public void update(UUID customerId, Customer customer) {
        var customerEntity = customerRepository.findById(customerId);

        customerEntity.ifPresent(entity -> {
            entity.changeCostumer(customer);
            entity.setUpdatedAt(LocalDateTime.now());
            customerRepository.save(entity);
        });
    }
}
