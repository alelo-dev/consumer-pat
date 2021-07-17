package br.com.alelo.consumer.consumerpat.dto;

import br.com.alelo.consumer.consumerpat.enums.CardType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO of the buy request
 *
 * @author mcrj
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("RequestBuyDTO")
public class RequestBuyDTO implements Serializable {

    @ApiModelProperty(value = "Type of establishment - Values { 0 - FOOD; 1 - DRUGSTORE; 2 - FUEL }")
    private CardType establishmentType;

    @ApiModelProperty(value = "Name of establishment")
    private String establishmentName;

    @ApiModelProperty(value = "Number of card")
    private Long cardNumber;

    @ApiModelProperty(value = "Description of product")
    private String productDescription;

    @ApiModelProperty(value = "Value of buy")
    private BigDecimal value;

    @JsonIgnore
    private BigDecimal newValue;
}
