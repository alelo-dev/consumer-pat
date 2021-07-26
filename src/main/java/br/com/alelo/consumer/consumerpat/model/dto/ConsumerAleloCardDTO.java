package br.com.alelo.consumer.consumerpat.model.dto;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 24/07/2021 | 12:09
 */

import br.com.alelo.consumer.consumerpat.model.enums.AleloCardTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ConsumerAleloCardDTO {

    private Integer id;
    private AleloCardTypeEnum type;
    private String number;
    private BigDecimal balance;
}
