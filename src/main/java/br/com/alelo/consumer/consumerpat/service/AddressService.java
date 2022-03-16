package br.com.alelo.consumer.consumerpat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.entity.Address;
import br.com.alelo.consumer.consumerpat.respository.AddressRepository;

@Service
public class AddressService {
 
    private final AddressRepository repository;

    @Autowired
    public AddressService(AddressRepository repository) {
        this.repository = repository;
    }

    public List<Address> findAllByConsumer(String consumerName) {
        return this.repository.findByConsumerName(consumerName);
    }

    public Address createAddress(Address entity) {
        return this.repository.save(entity);
    }

    public Address updateAddress(Address address, Integer addressId) {
        final var updatedAddress = this.repository.findById(addressId)
            .map(entity -> {
                entity.setType(address.getType());
                entity.setCity(address.getCity());
                entity.setComplement(address.getComplement());
                entity.setCountry(address.getCountry());
                entity.setNumber(address.getNumber());
                entity.setPortalCode(address.getPortalCode());
                entity.setStreet(address.getStreet());
                return this.repository.save(entity);
            }).orElseThrow();

        return updatedAddress;
    }

}
