package br.com.alelo.consumer.consumerpat.controller.mapper;

import br.com.alelo.consumer.consumerpat.controller.dto.CompraJsonRequest;
import br.com.alelo.consumer.consumerpat.models.Compra;

/**
 * @author renanravelli
 */
public class CompraMapper {

  public static Compra map(CompraJsonRequest request) {
    return new Compra(request.getEstablishmentType(), request.getEstablishmentName(),
        request.getProductDescription(), request.getValue());
  }

}
