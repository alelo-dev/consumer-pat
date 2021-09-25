package br.com.alelo.consumer.consumerpat.mapper;

import br.com.alelo.consumer.consumerpat.dto.CustomerDto;
import br.com.alelo.consumer.consumerpat.entity.Customer;
import org.springframework.stereotype.Component;

/*
Poderia ser utilizado alguma lib como o MapStruct.
Como não é permitido acrescentar libs e mexer no pom fiz manualmente
 */
@Component
public class CustomerMapper implements Mapper<Customer, CustomerDto> {

    @Override
    public Customer mapDtoToModel(final CustomerDto dto) {
        return Customer.builder()
                .name(dto.getName())
                .documentNumber(dto.getDocumentNumber())
                .birthDate(dto.getBirthDate())
                .mobilePhoneNumber(dto.getMobilePhoneNumber())
                .residencePhoneNumber(dto.getResidencePhoneNumber())
                .phoneNumber(dto.getPhoneNumber())
                .email(dto.getEmail())
                .street(dto.getStreet())
                .number(dto.getNumber())
                .city(dto.getCity())
                .country(dto.getCountry())
                .portalCode(dto.getPortalCode())
                .build();
    }

    @Override
    public CustomerDto mapModelToDto(final Customer model) {
        return CustomerDto.builder()
                .id(model.getId())
                .name(model.getName())
                .documentNumber(model.getDocumentNumber())
                .birthDate(model.getBirthDate())
                .mobilePhoneNumber(model.getMobilePhoneNumber())
                .residencePhoneNumber(model.getResidencePhoneNumber())
                .phoneNumber(model.getPhoneNumber())
                .email(model.getEmail())
                .street(model.getStreet())
                .number(model.getNumber())
                .city(model.getCity())
                .country(model.getCountry())
                .portalCode(model.getPortalCode())
                .build();
    }

    @Override
    public Customer updateModelTarget(final Customer target, final CustomerDto origin) {
        target.setName(origin.getName());
        target.setDocumentNumber(origin.getDocumentNumber());
        target.setBirthDate(origin.getBirthDate());
        target.setMobilePhoneNumber(origin.getMobilePhoneNumber());
        target.setResidencePhoneNumber(origin.getResidencePhoneNumber());
        target.setPhoneNumber(origin.getPhoneNumber());
        target.setEmail(origin.getEmail());
        target.setStreet(origin.getStreet());
        target.setNumber(origin.getNumber());
        target.setCity(origin.getCity());
        target.setCountry(origin.getCountry());
        target.setPortalCode(origin.getPortalCode());

        return target;
    }

}
