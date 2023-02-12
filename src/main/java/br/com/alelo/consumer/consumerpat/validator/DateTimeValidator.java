package br.com.alelo.consumer.consumerpat.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DateTimeValidator implements ConstraintValidator<DateTimeValid, String> {

    private String dateFormat;

    @Override
    public void initialize(DateTimeValid constraintAnnotation) {
        dateFormat = constraintAnnotation.format();
    }

    @Override
    public boolean isValid(String strDate, ConstraintValidatorContext context) {
        try {
            DateFormat sdf = new SimpleDateFormat(this.dateFormat);
            sdf.setLenient(false);
            sdf.parse(strDate);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}