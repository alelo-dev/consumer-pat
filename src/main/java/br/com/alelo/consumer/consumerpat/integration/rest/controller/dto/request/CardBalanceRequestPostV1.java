package br.com.alelo.consumer.consumerpat.integration.rest.controller.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardBalanceRequestPostV1 {
    private String cardCode;
    private BigDecimal value;
}
