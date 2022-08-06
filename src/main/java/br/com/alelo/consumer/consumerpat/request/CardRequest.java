package br.com.alelo.consumer.consumerpat.request;

import br.com.alelo.consumer.consumerpat.domain.CardType;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class CardRequest {

    @ApiModelProperty(required = true, notes = "Card Type", example = "FOOD", dataType = "br.com.alelo.consumer.consumerpat.domain.CardType")
    @NotNull
    private CardType cardtype;

    @ApiModelProperty(notes = "Card balance", example = "0", dataType = "java.math.BigDecimal")
    private BigDecimal cardBalance;

    @ApiModelProperty(required = true, notes = "Whether the card is active or not", example = "1", dataType = "java.lang.Boolean")
    @NotNull
    private boolean active;

    @ApiModelProperty(required = true, notes = "Attach the consumer to the card", example = "1", dataType = "java.lang.Long")
    @NotNull
    private Long consumerId;
}
