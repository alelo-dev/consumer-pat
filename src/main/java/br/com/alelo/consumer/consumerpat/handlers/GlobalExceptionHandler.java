package br.com.alelo.consumer.consumerpat.handlers;

import br.com.alelo.consumer.consumerpat.controller.dto.CustomErrorJsonResponse;
import br.com.alelo.consumer.consumerpat.exceptions.BusinessException;
import br.com.alelo.consumer.consumerpat.exceptions.NotFoundException;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

  private static final String ERRO_INTERNO = "Ocorreu um erro interno no servidor: %s";

  @ResponseBody
  @ExceptionHandler(BindException.class)
  @ResponseStatus(code = HttpStatus.BAD_REQUEST)
  public CustomErrorJsonResponse argumentNotValidException(BindException ex) {
    var result = ex.getBindingResult();
    List<Object> parametrosInvalidos = Arrays.asList(result.getFieldErrors()
        .stream()
        .map(DefaultMessageSourceResolvable::getDefaultMessage).toArray());
    return CustomErrorJsonResponse.builder()
        .status(HttpStatus.BAD_REQUEST)
        .message("Ocorreu um erro na valida\u00E7\u00E3o das informa\u00E7\u00F5es.")
        .fieldErrors(parametrosInvalidos)
        .build();
  }

  @ResponseBody
  @ExceptionHandler(NotFoundException.class)
  @ResponseStatus(code = HttpStatus.NOT_FOUND)
  public CustomErrorJsonResponse notFoundException(NotFoundException ex) {
    return CustomErrorJsonResponse.builder()
        .status(HttpStatus.NOT_FOUND)
        .message(ex.getMessage())
        .build();
  }

  @ResponseBody
  @ExceptionHandler(BusinessException.class)
  @ResponseStatus(code = HttpStatus.BAD_REQUEST)
  public CustomErrorJsonResponse businessException(BusinessException ex) {
    return CustomErrorJsonResponse.builder()
        .status(HttpStatus.BAD_REQUEST)
        .message(ex.getMessage())
        .build();
  }

  @ResponseBody
  @ExceptionHandler(Exception.class)
  @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
  public CustomErrorJsonResponse exception(Exception exception) {
    log.error(exception.getMessage(), exception);
    var msg = String.format(ERRO_INTERNO, exception.getMessage());
    return CustomErrorJsonResponse.builder()
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .message(msg)
        .build();
  }
}
