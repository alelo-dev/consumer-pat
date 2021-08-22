package br.com.alelo.consumerpat.core.v1.request;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Getter
public class CardRechargeV1RequestDto {

    private BigDecimal value;
}
