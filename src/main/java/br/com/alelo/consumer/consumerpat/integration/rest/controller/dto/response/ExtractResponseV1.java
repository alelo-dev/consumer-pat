package br.com.alelo.consumer.consumerpat.integration.rest.controller.dto.response;

import br.com.alelo.consumer.consumerpat.domain.entity.Extract;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExtractResponseV1 {

    private String extractCode;

    public static ExtractResponseV1 transformToResponse(Extract extract) {
        return ExtractResponseV1.builder().extractCode(extract.getExtractCode()).build();
    }

}
