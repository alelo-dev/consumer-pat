package br.com.alelo.consumer.consumerpat.dto;

import br.com.alelo.consumer.consumerpat.entity.CardType;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class BuyDTO {
    public final String establishmentName;
    public final String productDescription;
    public final BigDecimal value;
    public final int establishmentType;

    @JsonCreator
    public BuyDTO(@JsonProperty("establishmentName") String establishmentName,
                  @JsonProperty("productDescription") String productDescription,
                  @JsonProperty("value") BigDecimal value,
                  @JsonProperty("establishmentType") Integer establishmentType) {
        this.establishmentName = establishmentName;
        this.productDescription = productDescription;
        this.value = value.multiply(new BigDecimal(-1));
        this.establishmentType = establishmentType;
    }

    public CardType getEstablishmentType(){
        return EstablishmentTypeParse.parse(establishmentType);
    }
}
