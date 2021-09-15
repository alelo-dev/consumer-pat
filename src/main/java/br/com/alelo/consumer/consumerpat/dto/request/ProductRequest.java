package br.com.alelo.consumer.consumerpat.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="ProductRequest", description="Product's information")
public class ProductRequest {

    @JsonProperty(value = "description")
    @ApiModelProperty(value = "Purchased product description")
    private String description;

    @JsonProperty(value = "value")
    @ApiModelProperty(value = "Purchased product value")
    private double value;

}
