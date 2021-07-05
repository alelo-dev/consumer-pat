package br.com.alelo.consumer.consumerpat.integration.rest.controller.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CardRequestPutV1 {
    private String cardCode;
    private Long number;
    private String type;
}
