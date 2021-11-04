package br.com.alelo.consumer.consumerpat.dtos;

import br.com.alelo.consumer.consumerpat.enumeraters.ESTABLISHMENT;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode
public class Buy {

    private ESTABLISHMENT establishmentType;
    private String establishmentName;
    private Integer cardNumber;
    private String productDescription;
    private BigDecimal value;

}
