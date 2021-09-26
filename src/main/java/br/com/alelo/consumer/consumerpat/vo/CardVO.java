package br.com.alelo.consumer.consumerpat.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardVO {
    private Integer cardNumber;
    private String type;
    private String documentNumber;
}
