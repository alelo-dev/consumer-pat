package br.com.alelo.consumer.consumerpat.facade;

import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.controller.dto.AddressDto;
import br.com.alelo.consumer.consumerpat.controller.dto.AddressSaveDto;
import br.com.alelo.consumer.consumerpat.facade.converter.AddressConverter;
import br.com.alelo.consumer.consumerpat.service.AddressService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AddressFacade {

    private final AddressService service;

    public void save(final AddressSaveDto address) {

        var dto = AddressDto.builder().street(address.getStreet()).city(address.getCity())
                .consumerId(address.getConsumerId()).country(address.getCountry()).number(address.getNumber())
                .postalCode(address.getPostalCode()).build();

        service.save(AddressConverter.toEntity(dto));
    }

    public void update(final AddressDto address) {
        service.update(AddressConverter.toEntity(address));
    }

}
