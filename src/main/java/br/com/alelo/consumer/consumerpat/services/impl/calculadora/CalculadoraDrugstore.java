package br.com.alelo.consumer.consumerpat.services.impl.calculadora;

import java.math.BigDecimal;

public class CalculadoraDrugstore implements Calculadora {

  @Override
  public BigDecimal calcular(BigDecimal valor) {
    return valor;
  }
}
