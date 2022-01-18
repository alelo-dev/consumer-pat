package br.com.alelo.consumer.consumerpat.exception;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler{

	@Autowired
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String lMensagemTratada = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
		
		//String mensagemDesenvolvedor = ex.getCause() != null ? ex.getCause().toString() : ex.toString();
		String lMensagemOriginal = Optional.ofNullable(ex.getCause()).orElse(ex).toString();
		
		List<Erro> listaErros = Arrays.asList((new Erro(lMensagemTratada, lMensagemOriginal)));
		
		return handleExceptionInternal(ex, listaErros, headers, HttpStatus.BAD_REQUEST, request);
	}	
	
	@org.springframework.web.bind.annotation.ExceptionHandler({EmptyResultDataAccessException.class})
	public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex,
			WebRequest request) {
		
		String lMensagemTratada = messageSource.getMessage("recurso.nao-encontrado", null, LocaleContextHolder.getLocale());
		
		String mensagemOriginal = ex.toString();
		
		List<Erro> listaErros = Arrays.asList((new Erro(lMensagemTratada, mensagemOriginal)));
		
		return handleExceptionInternal(ex, listaErros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler({ApplicationException.class})
	public ResponseEntity<Object> handleApplicationException(ApplicationException ex,
			WebRequest request) {
		
		String lMensagemTratada = messageSource.getMessage("cartao.nao-encontrado", null, LocaleContextHolder.getLocale());
		
		String mensagemOriginal = ex.toString();
		
		List<Erro> listaErros = Arrays.asList((new Erro(lMensagemTratada, mensagemOriginal)));
		
		return handleExceptionInternal(ex, listaErros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	
	@org.springframework.web.bind.annotation.ExceptionHandler({EnvironmentException.class})
	public ResponseEntity<Object> handleAmbientException(EnvironmentException ex,
			WebRequest request) {
		
		String lMensagemTratada = messageSource.getMessage("erro.ambiente", null, LocaleContextHolder.getLocale());
		
		String mensagemOriginal = ex.toString();
		
		List<Erro> listaErros = Arrays.asList((new Erro(lMensagemTratada, mensagemOriginal)));
		
		return handleExceptionInternal(ex, listaErros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}	
	
	public static class Erro{
		
		private String mensagemTratada;
		private String mensagemOriginal;
		
		public Erro(String mensagemTratada, String mensagemOriginal) {
			this.mensagemTratada = mensagemTratada;
			this.mensagemOriginal = mensagemOriginal;
		}

		public String getMensagemTratada() {
			return mensagemTratada;
		}

		public String getMensagemOriginal() {
			return mensagemOriginal;
		}
	}	
	
}
