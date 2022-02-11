package br.com.alelo.consumer.consumerpat.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class BuyDTO {

    private int establishmentType;
    private String establishmentName;
    private String productDescription;
    private BigDecimal value;
    
}
