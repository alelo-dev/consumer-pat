package br.com.alelo.consumer.consumerpat.dto.input;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class CardBalanceInputDTO {

    @NotNull
    private Integer cardNumber;

    @NotNull
    private BigDecimal balance;

}
