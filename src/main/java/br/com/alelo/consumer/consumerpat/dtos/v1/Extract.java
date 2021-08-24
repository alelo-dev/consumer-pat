package br.com.alelo.consumer.consumerpat.dtos.v1;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Extract {

    private int id;
    private int establishmentNameId;
    private String establishmentName;
    private String productDescription;
    private LocalDate dateBuy;
    private int cardNumber;
    private BigDecimal value;

}
