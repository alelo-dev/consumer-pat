package br.com.alelo.consumer.consumerpat.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CardDTO {
    private Integer cardNumber;
    private Double value;
}
