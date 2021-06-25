package br.com.alelo.consumer.consumerpat.view.handler;

import java.net.ConnectException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.UnexpectedTypeException;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import br.com.alelo.consumer.consumerpat.exception.CustomException;
import br.com.alelo.consumer.consumerpat.exception.EntityNotFoundException;
import br.com.alelo.consumer.consumerpat.exception.ServiceException;
import br.com.alelo.consumer.consumerpat.view.exception.ErrorData;
import br.com.alelo.consumer.consumerpat.view.exception.ErrorResponse;
import br.com.alelo.consumer.consumerpat.view.exception.ValidationException;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

	@ExceptionHandler(ValidationException.class)
	@ResponseBody
	public ResponseEntity<ErrorResponse> handleFieldValidationException(final ValidationException ex) {
		final ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.toString(), ex.getErrors());
		log.info("ValidationException: {}", error);

		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseBody
	public ResponseEntity<ErrorResponse> handleIllegalArgumentException(final IllegalArgumentException ex) {
		final ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.toString(), "Validation", ex.getMessage());
		log.error("IllegalArgumentException: {}", error);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ServiceException.class)
	@ResponseBody
	public ResponseEntity<ErrorResponse> handleFieldServiceException(final ServiceException ex) {
		final ErrorResponse error = new ErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY.toString(), ex.getType(), "Ocorreu um erro inesperado, contate o administrador do sistema!");
		log.error("ServiceException: {}", error);
		return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ExceptionHandler(EntityNotFoundException.class)
	@ResponseBody
	public ResponseEntity<ErrorResponse> handleEntityNotFoundException(final EntityNotFoundException ex) {
		final StringBuilder message = new StringBuilder();
		message.append("Entity ");
		Optional.ofNullable(ex.getType()).ifPresent(type -> message.append(type).append(" "));
		Optional.ofNullable(ex.getIdentifier()).ifPresent(id -> message.append("with identifier ").append(id).append(" "));
		message.append("not found.");
		if (!StringUtils.isEmpty(ex.getMessage())) {
			message.append(" (").append(ex.getMessage()).append(")");
		}
		final ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND.toString(), "Entity Not Found", "Ocorreu um erro inesperado, contate o administrador do sistema!");
		log.error("EntityNotFoundException: {}", error);
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}


	

	@ExceptionHandler(ConnectException.class)
	@ResponseBody
	public ResponseEntity<ErrorResponse> handleConnectException(final ConnectException ex) {
		final ErrorResponse error = new ErrorResponse(HttpStatus.GATEWAY_TIMEOUT.toString(), "Gateway timeout", "Serviço indisponível!");
		log.info("ConnectException: {}", error);
		return new ResponseEntity<>(error, HttpStatus.GATEWAY_TIMEOUT);
	}

	/*@ExceptionHandler(HttpHostConnectException.class)
	@ResponseBody
	public ResponseEntity<ErrorResponse> handleHttpHostConnectException(final HttpHostConnectException ex) {
		final ErrorResponse error = new ErrorResponse(HttpStatus.GATEWAY_TIMEOUT.toString(), "Gateway timeout", "Serviço indisponível!");
		log.info("HttpHostConnectException: {}", error);
		return new ResponseEntity<>(error, HttpStatus.GATEWAY_TIMEOUT);
	}*/

	@ExceptionHandler(BindException.class)
	@ResponseBody
	public ResponseEntity<ErrorResponse> handleSpringBindException(final BindException ex) {
		final List<ErrorData> errorsData = ex.getFieldErrors().stream().map(error -> ErrorData.builder().field(error.getField()).type("Invalid Parameter").message(error.getDefaultMessage()).build()).collect(Collectors.toList());
		final ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.toString(), errorsData);
		log.info("BindException: {}", error);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseBody
	public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatch(final MethodArgumentTypeMismatchException ex) {

		log.error("Method Argument Type Mismatch {}", ex);

		final StringBuilder errorMessage = new StringBuilder();
		errorMessage.append(ex.getName()).append(" should be of type ");
		Optional.ofNullable(ex.getRequiredType()).ifPresent(requiredType -> errorMessage.append(requiredType.getName()));

		final ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.toString(), ex.getClass().getName(), errorMessage.toString());

		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseBody
	public ResponseEntity<ErrorResponse> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex) {
		log.error("HttpMessageNotReadableException: {}", ex);

		if (ex.getCause() instanceof com.fasterxml.jackson.databind.exc.InvalidFormatException) {

			com.fasterxml.jackson.databind.exc.InvalidFormatException invalidFormatException = (com.fasterxml.jackson.databind.exc.InvalidFormatException) ex.getCause();

			if (invalidFormatException.getCause() instanceof java.time.DateTimeException) {
				final ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.toString(), "parseDate", "Data inválida!");
				log.info("DateTimeException: {}", error);
				return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
			}
		}

		final ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.toString(), "Invalid Http Message", ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ResponseEntity<ErrorResponse> handleFieldServiceException(final Exception ex) {
		log.error("Uncaught Exception: {}", ex);
		final ErrorResponse error = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.toString(), ex.getClass().getName(), "Ocorreu um erro inesperado, contate o administrador do sistema!");
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "Not Acceptable")
	@ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
	public void handleNotAcceptableException(final HttpMediaTypeNotAcceptableException ex) {
		log.error("Not Acceptable Exception", ex);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseBody
	public ResponseEntity<ErrorResponse> handleMethodNotAllowed(final HttpRequestMethodNotSupportedException ex) {
		log.error("MethodNotAllowed: {}", ex);
		final ErrorResponse error = new ErrorResponse(HttpStatus.METHOD_NOT_ALLOWED.toString(), HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase(), ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.METHOD_NOT_ALLOWED);
	}

	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	@ResponseBody
	public ResponseEntity<ErrorResponse> handleUnsupportedMediaType(final HttpMediaTypeNotSupportedException ex) {
		log.error("UnsupportedMediaType: {}", ex);
		final ErrorResponse error = new ErrorResponse(HttpStatus.UNSUPPORTED_MEDIA_TYPE.toString(), HttpStatus.UNSUPPORTED_MEDIA_TYPE.getReasonPhrase(), ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex) {

		final List<ErrorData> errorsData = ex.getBindingResult().getFieldErrors().stream().map(error -> ErrorData.builder().field(error.getField()).type("Invalid Parameter").message(error.getDefaultMessage()).build()).collect(Collectors.toList());

		final ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.toString(), errorsData);
		log.info("Method Argument Not Valid: {}", error);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UnexpectedTypeException.class)
	@ResponseBody
	public ResponseEntity<ErrorResponse> handleUnexpectedTypeException(final UnexpectedTypeException ex) {
		log.error("UnexpectedType Exception: {}", ex);
		final ErrorResponse error = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.toString(), ex.getClass().getName(), "Ocorreu um erro inesperado, contate o administrador do sistema!");
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

	@ExceptionHandler(CustomException.class)
	@ResponseBody
	public ResponseEntity<ErrorResponse> handleCustomException(final CustomException ex) {
		log.error("Custom Exception: {}", ex);
		final ErrorResponse error = new ErrorResponse(ex.getHttpStatus().toString(), ex.getClass().getName(), ex.getMessage());
		return new ResponseEntity<>(error, ex.getHttpStatus());
	}

	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseBody
	public ResponseEntity<Object> handleConstraintViolationException(final ConstraintViolationException ex) {
		log.error("ConstraintViolation Exception: {}", ex);
		final ErrorResponse error = new ErrorResponse(HttpStatus.CONFLICT.toString(), ex.getClass().getName(), ex.getMessage());

		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

}
