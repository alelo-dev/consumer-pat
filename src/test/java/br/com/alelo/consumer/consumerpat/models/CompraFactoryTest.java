package br.com.alelo.consumer.consumerpat.models;

import java.math.BigDecimal;

public class CompraFactoryTest {

  public static Compra criar() {
    return Compra.builder()
        .establishmentName("Teste")
        .establishmentType(2)
        .productDescription("Teste")
        .value(BigDecimal.TEN)
        .build();
  }

  public static Compra criar(int establishmentType) {
    return Compra.builder()
        .establishmentName("Teste")
        .establishmentType(establishmentType)
        .productDescription("Teste")
        .value(BigDecimal.TEN)
        .build();
  }

}
