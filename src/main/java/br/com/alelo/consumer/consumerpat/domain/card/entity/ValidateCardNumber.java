package br.com.alelo.consumer.consumerpat.domain.card.entity;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
@Pattern(regexp = "\\d{16}", message = "Card number invalid")
@Constraint(validatedBy = { })
public @interface ValidateCardNumber {

    String message() default "Card number invalid";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
