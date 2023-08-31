package br.com.alelo.consumer.consumerpat.mapping;

import br.com.alelo.consumer.consumerpat.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.enums.CompanyType;

import java.util.Objects;

public class ConsumerMapping {

    private ConsumerMapping(){}

    public static Consumer to(ConsumerDTO dto) {
        return Consumer
                .builder()
                .id(dto.getId())
                .birthDate(dto.getBirthDate())
                .documentNumber(dto.getDocumentNumber())
                .name(dto.getName())
                .contact(ContactMapping.to(dto))
                .address(AddressMapping.to(dto))
                .cards(CardMapping.to(dto))
                .build();
    }

    public static ConsumerDTO toDto(Consumer consumer) {
            var dto =  ConsumerDTO
                    .builder()
                    .id(consumer.getId())
                    .birthDate(consumer.getBirthDate())
                    .name(consumer.getName())
                    .documentNumber(consumer.getDocumentNumber())
                    .build();
            if(Objects.nonNull(consumer.getContact())) {
                dto.setMobilePhoneNumber(consumer.getContact().getMobilePhoneNumber());
                dto.setResidencePhoneNumber(consumer.getContact().getResidencePhoneNumber());
                dto.setPhoneNumber(consumer.getContact().getPhoneNumber());
                dto.setEmail(consumer.getContact().getEmail());
            }
            if(Objects.nonNull(consumer.getAddress())) {
                dto.setStreet(consumer.getAddress().getStreet());
                dto.setNumber(consumer.getAddress().getNumber());
                dto.setCity(consumer.getAddress().getCity());
                dto.setCountry(consumer.getAddress().getCountry());
                dto.setPostalCode(consumer.getAddress().getPostalCode());
            }
            if(Objects.nonNull(consumer.getCards())) {
                consumer.getCards().forEach(card -> {
                    if(CompanyType.FOOD.equals(card.getCompanyType())) {
                        dto.setFoodCardNumber(card.getCardNumber());
                        dto.setFoodCardBalance(card.getCardBalance());
                    } else if(CompanyType.DRUGSTORE.equals(card.getCompanyType())) {
                        dto.setDrugstoreCardNumber(card.getCardNumber());
                        dto.setDrugstoreCardBalance(card.getCardBalance());
                    } else if(CompanyType.FUEL.equals(card.getCompanyType())) {
                        dto.setFuelCardNumber(card.getCardNumber());
                        dto.setFuelCardBalance(card.getCardBalance());
                    }
                });
            }
            return dto;
    }

}
