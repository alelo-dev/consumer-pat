package br.com.alelo.consumer.consumerpat.model.dto;

import br.com.alelo.consumer.consumerpat.utils.exception.ProcessException;
import com.sun.istack.NotNull;
import lombok.Data;

import java.util.Objects;

@Data
public class DtoBuy {

  private int establishmentType;

  private String establishmentName;

  private long cardNumber;

  private String productDescription;

  private double value;

  /**Esse construtor poderia ser desconsiderado, se criassemos anotações para validar a regra de cada campo */
  public DtoBuy(int establishmentType, @NotNull String establishmentName, long cardNumber, @NotNull String productDescription, double value)
      throws ProcessException {
    setValue(value);
    setEstablishmentType(establishmentType);
    setEstablishmentName(establishmentName);
    setCardNumber(cardNumber);
    this.productDescription = productDescription;
  }

  private void setCardNumber(long cardNumber)
      throws ProcessException {
    if (String.valueOf(cardNumber).length() != 16) {
      throw  new ProcessException("cardNumber must be 16 characters");
    }
    this.cardNumber = cardNumber;
  }

  /** Aqui seria possivel usar anotações do javax para validar valor minimo*/
  private void setValue(double value) throws ProcessException {
    if (value <= 0.00) {
      throw  new ProcessException("value must be bigger than 0");
    }
    this.value = value;
  }

  /** Aqui seria possivel usar anotações do javax para validar valor minimo*/
  private void setEstablishmentType(int establishmentType)
      throws ProcessException {
    if (Objects.isNull(EstablishmentTypeEnum.getEstablishmentType(establishmentType))) {
      throw new ProcessException("establishmentType invalid");
    }
    this.establishmentType = establishmentType;
  }

  /** Aqui seria possivel usar anotações do javax para validar valor minimo*/
  private void setEstablishmentName(String establishmentName)
      throws ProcessException {
    if (Objects.isNull(EstablishmentTypeEnum.getEstablishmentName(establishmentName))) {
      throw  new ProcessException("establishmentName invalid");
    }
    this.establishmentName = establishmentName;
  }
}
