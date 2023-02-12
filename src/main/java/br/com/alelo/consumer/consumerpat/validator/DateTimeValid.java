package br.com.alelo.consumer.consumerpat.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = DateTimeValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DateTimeValid {

    public String message() default "Invalid datetime!";
    public String format() default "yyyy-MM-dd";
    public Class<?>[] groups() default {};
    public Class<? extends Payload>[] payload() default {};

}