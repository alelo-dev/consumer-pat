package br.com.alelo.consumer.consumerpat.dto.input;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class ProductInputDTO {

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private Long establishmentId;
}
