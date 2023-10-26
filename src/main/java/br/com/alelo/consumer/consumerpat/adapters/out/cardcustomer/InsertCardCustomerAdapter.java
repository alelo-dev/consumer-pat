package br.com.alelo.consumer.consumerpat.adapters.out.cardcustomer;

import br.com.alelo.consumer.consumerpat.adapters.out.cardcustomer.repository.CardCustomerRepository;
import br.com.alelo.consumer.consumerpat.adapters.out.cardcustomer.repository.mapper.CardCustomerEntityMapper;
import br.com.alelo.consumer.consumerpat.adapters.out.customer.repository.CustomerRepository;
import br.com.alelo.consumer.consumerpat.application.core.domain.cardcustomer.CardCustomer;
import br.com.alelo.consumer.consumerpat.application.ports.out.cardcustomer.InsertCardCustomerOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class InsertCardCustomerAdapter implements InsertCardCustomerOutputPort {

    private final CardCustomerRepository cardCustomerRepository;
    private final CustomerRepository customerRepository;
    private final CardCustomerEntityMapper cardCustomerEntityMapper;

    @Override
    public void insert(UUID customerId, CardCustomer cardCustomer) {
        var customer = customerRepository.findById(customerId);

        var cardCostumerEntity = cardCustomerEntityMapper.toCardCustomerEntity(cardCustomer);

        customer.ifPresent(cardCostumerEntity::setCustomer);

        cardCustomerRepository.save(cardCostumerEntity);
    }
}
