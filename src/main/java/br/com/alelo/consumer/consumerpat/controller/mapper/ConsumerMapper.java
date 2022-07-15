package br.com.alelo.consumer.consumerpat.controller.mapper;

import br.com.alelo.consumer.consumerpat.controller.dto.ConsumerJsonRequest;
import br.com.alelo.consumer.consumerpat.controller.dto.ConsumerJsonResponse;
import br.com.alelo.consumer.consumerpat.entity.Consumer;

/**
 * @author renanravelli
 */
public class ConsumerMapper {

  public static Consumer map(ConsumerJsonRequest request) {
    return new Consumer(null, request.getName(),
        request.getDocumentNumber(), request.getBirthDate(),
        request.getMobilePhoneNumber(), request.getResidencePhoneNumber(),
        request.getPhoneNumber(), request.getEmail(),
        request.getStreet(), request.getNumber(), request.getCity(), request.getCountry(),
        request.getPortalCode());
  }

  public static ConsumerJsonResponse map(Consumer consumer) {
    return new ConsumerJsonResponse(consumer.getId(), consumer.getName(),
        consumer.getDocumentNumber(), consumer.getBirthDate(),
        consumer.getMobilePhoneNumber(), consumer.getResidencePhoneNumber(),
        consumer.getPhoneNumber(), consumer.getEmail(),
        consumer.getStreet(), consumer.getNumber(), consumer.getCity(), consumer.getCountry(),
        consumer.getPortalCode());
  }

}
