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

    public AddressORM save(AddressDTO newAddress) {
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
}
