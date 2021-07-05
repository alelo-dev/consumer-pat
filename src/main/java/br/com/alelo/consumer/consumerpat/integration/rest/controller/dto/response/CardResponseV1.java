package br.com.alelo.consumer.consumerpat.integration.rest.controller.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CardResponseV1 {
    private String cardCode;
}
