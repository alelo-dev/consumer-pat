package br.com.alelo.consumer.consumerpat.dto.input;

import br.com.alelo.consumer.consumerpat.entity.CardTypeEnum;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class CardInputDTO {

    @NotNull
    private CardTypeEnum cardType;

    @NotNull
    private Integer cardNumber;

    @NotNull
    private BigDecimal balance;

    @NotNull
    private Long consumerId;
}
