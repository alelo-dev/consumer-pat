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
@ApiModel(value="CardRequest", description="Card's information")
public class CardRequest {

    @JsonProperty(value = "type")
    @ApiModelProperty(value = "Benefit card type")
    private BusinessType type;

    @JsonProperty(value = "number")
    @ApiModelProperty(value = "Card's identificator number")
    private int number;
}
