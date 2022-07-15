package br.com.alelo.consumer.consumerpat.web.vo.extract;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewExtractFormVO implements Serializable {

    @NotNull(message = "EstablishmentType cannot be null")
    private Integer establishmentType;

    @NotNull(message = "EstablishmentId cannot be null")
    private Long establishmentId;

    @NotNull(message = "EstablishmentName cannot be null")
    @NotBlank(message = "EstablishmentName cannot be null")
    private String establishmentName;

    @NotNull(message = "ProductDescription cannot be null")
    @NotBlank(message = "ProductDescription cannot be null")
    private String productDescription;

    @NotNull(message = "Value cannot be null")
    @Min(value = 0L, message = "The smallest amount allowed for the value is zero")
    private BigDecimal value;

    @NotNull(message = "CardNumber cannot be null")
    private Long cardNumber;

}
