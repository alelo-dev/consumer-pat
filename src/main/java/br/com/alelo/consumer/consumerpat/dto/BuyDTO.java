package br.com.alelo.consumer.consumerpat.dto;

import br.com.alelo.consumer.consumerpat.enums.CardType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@ApiModel("BuyDTO")
public class BuyDTO {

    @ApiModelProperty(value = "Tipo do estabelecimento")
    private CardType establishmentType;

    @ApiModelProperty(value = "Nome do estabelecimento")
    private String establishmentName;

    @ApiModelProperty(value = "Número do cartão")
    private Long cardNumber;

    @ApiModelProperty(value = "Descrição do produto")
    private String productDescription;

    @ApiModelProperty(value = "Valor")
    private BigDecimal value;

    @JsonIgnore
    private BigDecimal newValue;
}
