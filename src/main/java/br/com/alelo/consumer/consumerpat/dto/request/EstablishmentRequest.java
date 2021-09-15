package br.com.alelo.consumer.consumerpat.dto.request;

import br.com.alelo.consumer.consumerpat.entity.BusinessType;
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
@ApiModel(value="EstablishmentRequest", description="Establishment's information")
public class EstablishmentRequest {

    @JsonProperty(value = "id")
    private Integer id;

    @JsonProperty(value = "name")
    @ApiModelProperty(value = "Establishment's name")
    private String name;

    @JsonProperty(value = "type")
    @ApiModelProperty(value = "Establishment's business type")
    private BusinessType type;
}
