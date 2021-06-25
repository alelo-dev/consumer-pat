package br.com.alelo.consumer.consumerpat.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import br.com.alelo.consumer.consumerpat.view.exception.ValidationException;
import lombok.extern.slf4j.Slf4j;

/**
 * Utility class to validate generic operations.
 */
@Slf4j
public class ValidatorExpection {

	private ValidatorExpection() {
	}

	/**
	 * Validates if the login is being used.
	 *
	 * @return {@link ValidationException} if the field is duplicated
	 */
	public static ValidationException newException(final String objectName, final String field, final String defaultMessage) {
		final List<FieldError> errors = new ArrayList<>();
		final FieldError error = new FieldError(objectName, field, defaultMessage);
		errors.add(error);
		final ValidationException ex = new ValidationException(defaultMessage, errors);
		log.error("ERROR while validating "+ objectName +", field: " + field, ex);
		return ex;
	}
	
	/**
	 * Validates if the login is being used.
	 *
	 * @return {@link ValidationException} if the field is duplicated
	 */
	public static ValidationException newException(final String defaultMessage) {
		final List<FieldError> errors = new ArrayList<>();
		final FieldError error = new FieldError("default", "default", defaultMessage);
		errors.add(error);
		final ValidationException ex = new ValidationException(defaultMessage, errors);
		log.error("ERROR while validating: ", ex);
		return ex;
	}
	
	/**
	 * Validates if the login is being used.
	 *
	 * @return {@link ValidationException} if the field is duplicated
	 */
	public static ValidationException newException(final List<FieldError> errors, final String defaultMessage) {
		final ValidationException ex = new ValidationException(errors);
		return ex;
	}
	
	/**
	 * Validates if the login is being used.
	 *
	 * @return {@link ValidationException} if the field is duplicated
	 */
	public static ValidationException duplicatedNameException() {
		final List<FieldError> errors = new ArrayList<>();
		final FieldError error = new FieldError("vo", "Nome", "Registro já existe");
		errors.add(error);
		final ValidationException ex = new ValidationException("Nome não é válido", errors);
		log.error("ERROR while validating name: {}", ex);
		return ex;
	}
	
	/**
	 * Validates .
	 *
	 * @return {@link ValidationException} 
	 */
	public static ValidationException newException(final List<ObjectError> objectErrors) {
		final List<FieldError> errors = new ArrayList<>();
		
		for (ObjectError objectError : objectErrors) {
			if (objectError instanceof FieldError) {
				errors.add((FieldError) objectError);
			} else {
				final FieldError error = new FieldError(objectError.getObjectName(), "default", objectError.getDefaultMessage());
				errors.add(error);
			}
		}
		
		final ValidationException ex = new ValidationException("default", errors);
		log.error("ERROR while validating: ", ex);
		
		return ex;
	}

}