package br.com.alelo.consumer.consumerpat.adapter.in.web.mapper;

import br.com.alelo.consumer.consumerpat.adapter.in.web.model.PurchaseOccurrenceDTO;
import br.com.alelo.consumer.consumerpat.domain.PurchaseOccurrence;

public class PurchaseOccurrenceDTOMapper {

  public static PurchaseOccurrence mapToDomain(final PurchaseOccurrenceDTO purchaseOccurrenceDTO) {

    return PurchaseOccurrence.builder()
        .establishmentType(purchaseOccurrenceDTO.getEstablishmentType())
        .establishmentName(purchaseOccurrenceDTO.getEstablishmentName())
        .productDescription(purchaseOccurrenceDTO.getProductDescription())
        .cardNumber(purchaseOccurrenceDTO.getCardNumber()).value(purchaseOccurrenceDTO.getValue())
        .build();

  }
  
}
