package br.com.alelo.consumer.consumerpat.controller.mapper;

import br.com.alelo.consumer.consumerpat.controller.dto.CartaoJsonRequest;
import br.com.alelo.consumer.consumerpat.controller.dto.CartaoJsonResponse;
import br.com.alelo.consumer.consumerpat.entity.Cartao;

/**
 * @author renanravelli
 */
public class CartaoMapper {

  public static Cartao map(CartaoJsonRequest request) {
    return new Cartao(null, request.getTipo(), request.getNumber(), request.getBalance(), null);
  }

  public static CartaoJsonResponse map(Cartao cartao) {
    return CartaoJsonResponse.builder()
        .id(cartao.getId())
        .tipo(cartao.getTipo())
        .number(cartao.getNumber())
        .balance(cartao.getBalance())
        .build();
  }

}
