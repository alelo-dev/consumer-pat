package br.com.alelo.consumer.consumerpat.mapper;

import br.com.alelo.consumer.consumerpat.domain.Client;
import br.com.alelo.consumer.consumerpat.dto.ClientDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ClientMapper {

    public ClientDTO toDTO(Client entity) {
        return ClientDTO.builder()
                        .documentNumber(Optional.ofNullable(entity.getDocumentNumber()).orElse(null))
                        .id(Optional.ofNullable(entity.getId()).orElse(null))

                        .city(Optional.ofNullable(entity.getCity()).orElse(null))
                        .country(Optional.ofNullable(entity.getCountry()).orElse(null))
                        .number(Optional.of(entity.getNumber()).orElse(null))
                        .portalCode(Optional.of(entity.getPortalCode()).orElse(null))
                        .street(Optional.ofNullable(entity.getStreet()).orElse(null))
                        .email(Optional.ofNullable(entity.getEmail()).orElse(null))
                        .mobilePhoneNumber(Optional.ofNullable(entity.getMobilePhoneNumber()).orElse(null))
                        .phoneNumber(Optional.ofNullable(entity.getPhoneNumber()).orElse(null))
                        .residencePhoneNumber(Optional.ofNullable(entity.getResidencePhoneNumber()).orElse(null))

                        .birthDate(Optional.ofNullable(entity.getBirthDate()).orElse(null))
                       // .creationDate(Optional.ofNullable(entity.getCreationDate()).orElse(null))
                        .name(Optional.ofNullable(entity.getName()).orElse(null))
                       // .updateDate(Optional.ofNullable(entity.getUpdateDate()).orElse(null))
                .build();
    }

    public Client toEntity(ClientDTO dto) {
        return Client.builder()
                .documentNumber(dto.getDocumentNumber())
                .id(Optional.ofNullable(dto.getId()).orElse(null))
                .city(Optional.ofNullable(dto.getCity()).orElse(null))
                .country(Optional.ofNullable(dto.getCountry()).orElse(null))
                .number(Optional.of(dto.getNumber()).orElse(null))
                .portalCode(Optional.of(dto.getPortalCode()).orElse(null))
                .street(Optional.ofNullable(dto.getStreet()).orElse(null))
                .email(Optional.ofNullable(dto.getEmail()).orElse(null))
                .mobilePhoneNumber(Optional.ofNullable(dto.getMobilePhoneNumber()).orElse(null))
                .phoneNumber(Optional.ofNullable(dto.getPhoneNumber()).orElse(null))
                .residencePhoneNumber(Optional.ofNullable(dto.getResidencePhoneNumber()).orElse(null))
                .birthDate(Optional.ofNullable(dto.getBirthDate()).orElse(null))
                //.creationDate(Optional.ofNullable(dto.getCreationDate()).orElse(null))
                .name(Optional.ofNullable(dto.getName()).orElse(null))
                //.updateDate(Optional.ofNullable(dto.getUpdateDate()).orElse(null))
                .build();
    }

    public List<Client> toEntityList(List<ClientDTO> dtos) {
        return dtos.stream().map(this::toEntity).collect(Collectors.toList());
    }

    public List<ClientDTO> toDTOList(List<Client> entityses) {
        return entityses.stream().map(this::toDTO).collect(Collectors.toList());
    }

}