package br.com.alelo.consumer.consumerpat.adapters.out.customer;

import br.com.alelo.consumer.consumerpat.adapters.out.customer.repository.CustomerRepository;
import br.com.alelo.consumer.consumerpat.adapters.out.customer.repository.entity.CustomerEntity;
import br.com.alelo.consumer.consumerpat.adapters.out.customer.repository.mapper.CustomerEntityMapper;
import br.com.alelo.consumer.consumerpat.application.core.domain.customer.Customer;
import br.com.alelo.consumer.consumerpat.application.ports.out.customer.FindCustomerOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FindCustomerAdapter implements FindCustomerOutputPort {

    private final CustomerRepository customerRepository;
    private final CustomerEntityMapper customerEntityMapper;

    @Override
    public List<Customer> findAllCustomer(Pageable pageable) {
        Page<CustomerEntity> customerEntityList = customerRepository.findAll(pageable);

        return customerEntityList.stream().map(customerEntityMapper::toCustomer).collect(Collectors.toList());
    }
    @Override
    public Optional<Customer> findCustomerById(UUID id) {
        var customerEntity = customerRepository.findById(id);
        return  customerEntity.map(customerEntityMapper::toCustomer);
    }
}
