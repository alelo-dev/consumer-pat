package br.com.alelo.consumer.consumerpat.exceptionhandler;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ConsumerExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler({ EmptyResultDataAccessException.class })
	public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex,
			WebRequest request) {
		String messageCause = messageSource.getMessage(ex.getMessage(), null, LocaleContextHolder.getLocale());
		String messageDetail = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(messageCause, messageDetail));
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

	@ExceptionHandler({ DataIntegrityViolationException.class } )
	public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
		String messageCause = messageSource.getMessage(ex.getMessage(), null, LocaleContextHolder.getLocale());
		String messageDetail = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(messageCause, messageDetail));
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	public static class Erro {

		private String messageCause;
		private String messageDetail;

		public Erro(String messageCause, String messageDetail) {
			this.messageCause = messageCause;
			this.messageDetail = messageDetail;
		}

		public String getMensagemUsuario() {
			return messageCause;
		}

		public String getMensagemDesenvolvedor() {
			return messageDetail;
		}

	}
}
