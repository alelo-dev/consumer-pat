package br.com.alelo.consumer.consumerpat.web.vo.card;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCardFormVO implements Serializable {

    @NotNull(message = "Value cannot be null")
    @Min(value = 0L, message = "The smallest amount allowed for the value is zero")
    private BigDecimal value;

}
