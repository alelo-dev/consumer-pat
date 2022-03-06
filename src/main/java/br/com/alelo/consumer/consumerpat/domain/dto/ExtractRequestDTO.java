package br.com.alelo.consumer.consumerpat.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel
public class ExtractRequestDTO {

    @ApiModelProperty(name = "establishmentName", example = "Fort Atacadista")
    private String establishmentName;

    @ApiModelProperty(name = "productDescription", example = "Frauda M Pants")
    private String productDescription;

    private Date dateBuy;
    private Integer cardNumber;

    @ApiModelProperty(name = "value", example = "49.90")
    private Double value;
}
