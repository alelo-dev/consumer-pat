package br.com.alelo.consumer.consumerpat.integration.rest.controller.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardBalanceRequestPostV1 {
    private Long number;
    private Double value;
}
