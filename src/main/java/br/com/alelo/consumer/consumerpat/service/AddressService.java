package br.com.alelo.consumer.consumerpat.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alelo.consumer.consumerpat.exception.NotFoundException;
import br.com.alelo.consumer.consumerpat.model.Address;
import br.com.alelo.consumer.consumerpat.repository.AddressRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    @Transactional
    public void save(final Address address) {
        addressRepository.save(address);
    }

    @Transactional
    public void update(final Address address) {
        var a = addressRepository.findByIdAndConsumer(address.getId(), address.getConsumer())
                .orElseThrow(() -> new NotFoundException("Address not found."));

        a.merge(address);
        addressRepository.save(a);
    }

}