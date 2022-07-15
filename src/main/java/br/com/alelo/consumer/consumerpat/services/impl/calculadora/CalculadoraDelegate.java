package br.com.alelo.consumer.consumerpat.services.impl.calculadora;

import br.com.alelo.consumer.consumerpat.models.Compra;
import java.util.HashMap;
import java.util.Map;

/**
 * @author renanravelli
 */
public class CalculadoraDelegate {

  public static Calculadora delegate(Compra compra) {
    Map<Integer, Calculadora> calculadoras = new HashMap<>();
    calculadoras.put(1, new CalculadoraFood());
    calculadoras.put(2, new CalculadoraDrugstore());
    calculadoras.put(3, new CalculadoraFuel());
    return calculadoras.get(compra.getEstablishmentType());
  }

}
