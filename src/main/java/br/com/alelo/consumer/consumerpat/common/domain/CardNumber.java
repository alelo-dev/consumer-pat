package br.com.alelo.consumer.consumerpat.common.domain;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
@Pattern(regexp = "\\d{16}", message = "Card number must be valid.")
@Constraint(validatedBy = { })
public @interface CardNumber {

    String message() default "Card number must be valid.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
