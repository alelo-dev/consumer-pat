package br.com.alelo.consumer.consumerpat.exception;

import br.com.alelo.consumer.consumerpat.entity.dto.ErrorDTO;
import br.com.alelo.consumer.consumerpat.entity.dto.ErrorResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class RequestHandlerException extends ResponseEntityExceptionHandler {


    private final static String INTERNAL_SERVER_ERROR = "Erro inesperado, refaça a operação";



    @ExceptionHandler(NoSuchElementFoundException.class)
    public ResponseEntity<Object> businessError(NoSuchElementFoundException exception) {
        log.info("Business Error,  message={}, cause={}", exception.getMessage(), exception.getCause());

        List<ErrorDTO> errorMessageDtoList = new ArrayList<>();

        errorMessageDtoList.add(ErrorDTO.builder()
                .message(INTERNAL_SERVER_ERROR)
                .build());

        ErrorResponseDTO errorResponseDto = ErrorResponseDTO.builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .errorList(errorMessageDtoList)
                .build();

        return handleException(errorResponseDto, exception);
    }


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> businessError(EntityNotFoundException exception) {
        log.info("Business Error,  message={}, cause={}", exception.getMessage(), exception.getCause());

        List<ErrorDTO> errorMessageDtoList = new ArrayList<>();

        errorMessageDtoList.add(ErrorDTO.builder()
                .message(exception.getMessage())
                .build());

        ErrorResponseDTO errorResponseDto = ErrorResponseDTO.builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .errorList(errorMessageDtoList)
                .build();

        return handleException(errorResponseDto, exception);
    }


    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> businessError(BusinessException exception) {
        log.info("Business Error,  message={}, cause={}", exception.getMessage(), exception.getCause());

        List<ErrorDTO> errorMessageDtoList = new ArrayList<>();



        errorMessageDtoList.add(ErrorDTO.builder()
                .message(exception.getMessage())
                .build());

        ErrorResponseDTO errorResponseDto = ErrorResponseDTO.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .errorList(errorMessageDtoList)
                .build();

        return handleException(errorResponseDto, exception);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> internalServerError(Exception exception) {
        log.info("Internet Error, message={}, cause={}", exception.getMessage(), exception.getCause());

        List<ErrorDTO> errorMessageDtoList = new ArrayList<>();

        errorMessageDtoList.add(ErrorDTO.builder()
                .message(INTERNAL_SERVER_ERROR)
                .build());

        ErrorResponseDTO errorResponseDto = ErrorResponseDTO.builder()
                .errorList(errorMessageDtoList)
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();

        return handleException(errorResponseDto, exception);
    }


    private ResponseEntity<Object> handleException(ErrorResponseDTO error, Exception e) {
        logger.error("Request Error", e);

        return ResponseEntity.status(error.getHttpStatus())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(error.getErrorList());
    }


}
