package br.com.alelo.consumer.consumerpat.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="PurchaseRequest", description="Purchase informations")
public class PurchaseRequest implements Serializable {

    @JsonProperty(value = "establishment")
    @ApiModelProperty(value = "Establishment information")
    private br.com.alelo.consumer.consumerpat.dto.request.EstablishmentRequest establishment;

    @JsonProperty(value = "card")
    @ApiModelProperty(value = "Card information")
    private br.com.alelo.consumer.consumerpat.dto.request.CardRequest card;

    @JsonProperty(value = "product")
    private br.com.alelo.consumer.consumerpat.dto.request.ProductRequest product;

    @ApiModelProperty(value = "Date/time of buy")
    private LocalDateTime buyDateTime;

}
