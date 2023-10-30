package br.com.alelo.consumer.consumerpat.adapters.out.cardcustomer;

import br.com.alelo.consumer.consumerpat.adapters.out.cardcustomer.repository.CardCustomerRepository;
import br.com.alelo.consumer.consumerpat.adapters.out.cardcustomer.repository.mapper.CardCustomerEntityMapper;
import br.com.alelo.consumer.consumerpat.application.core.domain.cardcustomer.CardCustomer;
import br.com.alelo.consumer.consumerpat.application.ports.out.cardcustomer.FindCardCustomerOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FindCardCustomerAdapter implements FindCardCustomerOutputPort {
    private final CardCustomerRepository cardCustomerRepository;
    private final CardCustomerEntityMapper cardCustomerEntityMapper;

    @Override
    public Set<CardCustomer> findCardCustomerById(UUID id) {
        var cardCustomerEntity = cardCustomerRepository.findByCustomerId(id);
        return  cardCustomerEntity.stream().map(cardCustomerEntityMapper::toCardCustomer).collect(Collectors.toSet());
    }
}
