package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.controller.dto.AddressDTO;
import br.com.alelo.consumer.consumerpat.entity.orm.AddressORM;
import br.com.alelo.consumer.consumerpat.respository.AddressRepository;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    private final AddressRepository allAddresses;

    public AddressService(AddressRepository allAddresses) {
        this.allAddresses = allAddresses;
    }

    public AddressORM persist(AddressDTO newAddress) {
        if (newAddress == null) {
            return null;
        }
        var address = new AddressORM();
        address.setCity(newAddress.getCity());
        address.setCountry(newAddress.getCountry());
        address.setNumber(newAddress.getNumber());
        address.setStreet(newAddress.getStreet());
        address.setPostalCode(newAddress.getPostalCode());
        return allAddresses.save(address);
    }

    public AddressORM merge(AddressORM current, AddressDTO latest) {
        if (current == null) {
            return persist(latest);
        }
        current.setCity(latest.getCity());
        current.setNumber(latest.getNumber());
        current.setStreet(latest.getStreet());
        current.setCountry(latest.getCountry());
        current.setPostalCode(latest.getPostalCode());
        return allAddresses.save(current);
    }
}
