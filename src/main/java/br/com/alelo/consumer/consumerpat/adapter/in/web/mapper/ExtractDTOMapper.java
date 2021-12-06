package br.com.alelo.consumer.consumerpat.adapter.in.web.mapper;

import br.com.alelo.consumer.consumerpat.adapter.in.web.model.ExtractDTO;
import br.com.alelo.consumer.consumerpat.domain.Extract;

public class ExtractDTOMapper {

  public static ExtractDTO mapToDTO(final Extract extract) {

    return ExtractDTO.builder().establishmentName(extract.getEstablishmentName())
        .productDescription(extract.getProductDescription()).dateBuy(extract.getDateBuy())
        .cardNumber(extract.getCardNumber()).value(extract.getValue()).build();

  }
}
