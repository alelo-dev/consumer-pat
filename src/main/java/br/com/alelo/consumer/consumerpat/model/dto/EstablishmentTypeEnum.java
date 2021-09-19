package br.com.alelo.consumer.consumerpat.model.dto;

import br.com.alelo.consumer.consumerpat.business.TransactionCardBusiness;
import br.com.alelo.consumer.consumerpat.business.impl.DrugStoreBusinessImpl;
import br.com.alelo.consumer.consumerpat.business.impl.FoodCardBusinessImpl;
import br.com.alelo.consumer.consumerpat.business.impl.FuelBusinessImpl;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum EstablishmentTypeEnum {

  FOOD(1, "FOOD", FoodCardBusinessImpl.class),
  DRUG_STONE(2, "DRUG STORE", DrugStoreBusinessImpl.class),
  FUEL(3, "FUEL", FuelBusinessImpl.class);

  private int type;
  private String name;
  private Class<? extends TransactionCardBusiness> clazz;

  public static EstablishmentTypeEnum getEstablishmentType(int code) {
    return Arrays.stream(values()).filter(establishmentTypeEnum -> establishmentTypeEnum.getType() == code)
        .findFirst().orElse(null);
  }

  public static EstablishmentTypeEnum getEstablishmentName(String name) {
    return Arrays.stream(values()).filter(establishmentTypeEnum -> establishmentTypeEnum.getName().equalsIgnoreCase(name))
        .findFirst().orElse(null);
  }
}
