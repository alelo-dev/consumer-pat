package br.com.alelo.consumer.consumerpat.parameter;

import br.com.alelo.consumer.consumerpat.entity.enumeration.PurchaseType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel("JSON de Compra")
public class PurchaseParameter {

    @ApiModelProperty(required = true)
    private int consumerId;

    @ApiModelProperty(required = true)
    private PurchaseType type;

    @ApiModelProperty(required = true)
    private String establishmentName;

    @ApiModelProperty(required = true)
    private String productDescription;

    @ApiModelProperty(required = true)
    private BigDecimal productValue;

    @ApiModelProperty(required = true)
    private String cardNumber;
}
