package br.com.alelo.consumer.consumerpat.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BuyVo {

    private Long cardNumber;
    private BigDecimal value;
    private Integer establishmentId;
    private Long establishmentType;
    private String productDescription;

}
