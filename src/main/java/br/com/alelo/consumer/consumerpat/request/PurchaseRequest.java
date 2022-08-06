package br.com.alelo.consumer.consumerpat.request;

import br.com.alelo.consumer.consumerpat.domain.Type;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

@Builder
@Data
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class PurchaseRequest {

    @ApiModelProperty(required = true, notes = "Card Type", example = "FOOD_SQUARE", dataType = "br.com.alelo.consumer.consumerpat.domain.EstablishmentType")
    @NotNull
    private Type establishmentType;

    @ApiModelProperty(required = true, notes = "Name of the establishment", example = "ATACADÃO", dataType = "java.lang.String")
    @NotBlank
    @Size(min = 1, max = 255)
    private String establishmentName;

    @ApiModelProperty(required = true, notes = "Card number", dataType = "java.lang.String")
    @NotBlank
    private String cardNumber;

    @ApiModelProperty(notes = "Product list", example = "[\"toddynho 200ml\",\"Biscoito Recheado Oreo Original 90g\"]", dataType = "[Ljava.lang.String;")
    @NotNull
    private List<String> products;

    @ApiModelProperty(notes = "Total purchase amount", example = "100", dataType = "java.math.BigDecimal")
    @NotNull
    private BigDecimal amount;
}
