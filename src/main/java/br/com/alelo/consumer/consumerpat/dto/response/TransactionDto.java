package br.com.alelo.consumer.consumerpat.dto.response;

import br.com.alelo.consumer.consumerpat.dto.request.EstablishmentRequest;
import br.com.alelo.consumer.consumerpat.dto.request.ProductRequest;
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
@ApiModel(value="Transaction", description="Purchase informations")
public class TransactionDto implements Serializable {

    @JsonProperty(value = "id")
    @ApiModelProperty(value = "Transaction id")
    private Integer id;

    @JsonProperty(value = "establishment")
    @ApiModelProperty(value = "Establishment information")
    private EstablishmentRequest establishment;

    @JsonProperty(value = "card")
    @ApiModelProperty(value = "Card information")
    private CardDto card;

    @JsonProperty(value = "product")
    private ProductRequest product;

    @ApiModelProperty(value = "Date/time of buy")
    private LocalDateTime buyDateTime;

}
