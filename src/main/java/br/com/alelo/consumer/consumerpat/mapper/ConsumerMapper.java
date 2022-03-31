package br.com.alelo.consumer.consumerpat.mapper;

import br.com.alelo.consumer.consumerpat.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.entity.*;
import org.springframework.stereotype.Component;

@Component
public class ConsumerMapper {

    public Consumer mapDtoToEntity(ConsumerDTO dto) {
        return Consumer.builder()
                .id(dto.getConsumerId())
                .address(Address.builder()
                        .street(dto.getStreet())
                        .city(dto.getCity())
                        .number(dto.getAddressNumber())
                        .portalCode(dto.getPortalCode())
                        .country(dto.getCountry())
                        .build())
                .contact(Contact.builder()
                        .email(dto.getEmail())
                        .mobilePhoneNumber(dto.getMobilePhoneNumber())
                        .phoneNumber(dto.getPhoneNumber())
                        .residencePhoneNumber(dto.getResidencePhoneNumber())
                        .build())
                .birthDate(dto.getBirthDate())
                .documentNumber(dto.getDocumentNumber())
                .name(dto.getName())
                .build();
    }

    public Consumer mapDtoToEntity(ConsumerDTO dto, Consumer entity) {
        entity.getAddress().setNumber(dto.getAddressNumber());
        entity.getAddress().setCity(dto.getCity());
        entity.getAddress().setCountry(dto.getCountry());
        entity.getAddress().setPortalCode(dto.getPortalCode());
        entity.getContact().setEmail(dto.getEmail());
        entity.getContact().setMobilePhoneNumber(dto.getMobilePhoneNumber());
        entity.getContact().setPhoneNumber(dto.getPhoneNumber());
        entity.getContact().setResidencePhoneNumber(dto.getResidencePhoneNumber());
        entity.setBirthDate(dto.getBirthDate());
        entity.setDocumentNumber(dto.getDocumentNumber());
        entity.setName(dto.getName());
        return entity;
    }

}
