package br.com.alelo.consumer.consumerpat.adapter.in.web.mapper;

import java.util.Objects;
import br.com.alelo.consumer.consumerpat.adapter.in.web.model.ConsumerUpdateDTO;
import br.com.alelo.consumer.consumerpat.domain.Address;
import br.com.alelo.consumer.consumerpat.domain.Consumer;
import br.com.alelo.consumer.consumerpat.domain.Contact;

public class ConsumerUpdateDTOMapper {

public static Consumer mapToDomain(ConsumerUpdateDTO updateConsumerDTO) {
    
    
    var consumerBuilder = Consumer.builder().id(updateConsumerDTO.getId()).name(updateConsumerDTO.getName())
        .documentNumber(updateConsumerDTO.getDocumentNumber()).birthDate(updateConsumerDTO.getBirthDate());
    
    if (Objects.nonNull(updateConsumerDTO.getContact())) {

      var contact =
          Contact.builder().mobilePhoneNumber(updateConsumerDTO.getContact().getMobilePhoneNumber())
              .residencePhoneNumber(updateConsumerDTO.getContact().getResidencePhoneNumber())
              .phoneNumber(updateConsumerDTO.getContact().getPhoneNumber())
              .email(updateConsumerDTO.getContact().getEmail()).build();
      consumerBuilder.contact(contact);

    }

    if (Objects.nonNull(updateConsumerDTO.getAddress())) {
      var address = Address.builder().street(updateConsumerDTO.getAddress().getStreet())
          .number(updateConsumerDTO.getAddress().getNumber()).city(updateConsumerDTO.getAddress().getCity())
          .country(updateConsumerDTO.getAddress().getCountry())
          .portalCode(updateConsumerDTO.getAddress().getPortalCode()).build();
      consumerBuilder.address(address);
    }
    
    return consumerBuilder.build();

  }
}
