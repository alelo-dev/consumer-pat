package br.com.alelo.consumer.consumerpat.services.impl.calculadora;

import java.math.BigDecimal;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculadoraTest {

  @Test
  public void deveRealizarCalculoDrugstore() {

    CalculadoraDrugstore calculadoraDrugstore = new CalculadoraDrugstore();

    BigDecimal resultado = calculadoraDrugstore.calcular(BigDecimal.TEN);

    Assertions.assertThat(resultado).isEqualTo(BigDecimal.TEN);
  }

  @Test
  public void deveRealizarCalculoFood() {

    CalculadoraFood calculadoraFood = new CalculadoraFood();

    BigDecimal resultado = calculadoraFood.calcular(BigDecimal.valueOf(10));

    Assertions.assertThat(resultado).isEqualTo(BigDecimal.valueOf(9.0));
  }

  @Test
  public void deveRealizarCalculoFuel() {

    CalculadoraFuel calculadoraFuel = new CalculadoraFuel();

    BigDecimal resultado = calculadoraFuel.calcular(BigDecimal.valueOf(10));

    Assertions.assertThat(resultado).isEqualTo(BigDecimal.valueOf(13.5));
  }

}
