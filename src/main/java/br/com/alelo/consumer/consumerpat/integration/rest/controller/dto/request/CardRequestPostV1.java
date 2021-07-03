package br.com.alelo.consumer.consumerpat.integration.rest.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardRequestPostV1 {
    private Long number;
    private Double balance;
    private Double value;
    private String type;
}
