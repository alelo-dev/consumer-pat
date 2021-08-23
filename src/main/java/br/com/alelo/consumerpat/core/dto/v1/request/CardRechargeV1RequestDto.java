package br.com.alelo.consumerpat.core.dto.v1.request;

import lombok.*;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CardRechargeV1RequestDto {

    private BigDecimal value;
}
