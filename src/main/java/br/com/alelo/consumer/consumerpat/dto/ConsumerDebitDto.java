package br.com.alelo.consumer.consumerpat.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Getter
@Setter
public class ConsumerDebitDto {

    @Min(value = 1, message = "establishmentType should not be less than 1")
    @Max(value = 3, message = "establishmentType should not be greater than 3")
    private int establishmentType;

    @Positive(message = "Establishment name id should greater than 1")
    private int establishmentNameId;

    @NotBlank(message = "Establishment name is required")
    private String establishmentName;

    @NotNull(message = "Card number is required")
    @Positive(message = "")
    private Long cardNumber;

    @NotBlank(message = "Product description is required")
    private String productDescription;

    @NotNull(message = "Value is required")
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer=17, fraction=2)
    private BigDecimal value;

}