package br.com.alelo.consumer.consumerpat.adapters.out.customer;

import br.com.alelo.consumer.consumerpat.adapters.out.customer.repository.CustomerRepository;
import br.com.alelo.consumer.consumerpat.adapters.out.customer.repository.mapper.CustomerEntityMapper;
import br.com.alelo.consumer.consumerpat.application.core.domain.customer.Customer;
import br.com.alelo.consumer.consumerpat.application.ports.out.customer.InsertCustomerOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class InsertCustomerAdapter implements InsertCustomerOutputPort {

    private final CustomerRepository customerRepository;
    private final CustomerEntityMapper customerEntityMapper;
    @Override
    public void insert(Customer customer) {
        customer.setCreatedAt(LocalDateTime.now());
        customerRepository.save(customerEntityMapper.toCustomerEntity(customer));
    }
}
