package br.com.alelo.consumer.consumerpat.helper;

import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.alelo.consumer.consumerpat.entity.Establishment;
import br.com.alelo.consumer.consumerpat.entity.Product;
import lombok.Data;

@Data
public class RequestConsumerBuy {
    
    private Establishment establishment;
    @NotEmpty
    private String cardNumber;
    private Product product;
    @NotNull
    private BigDecimal value;

}