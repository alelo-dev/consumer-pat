package br.com.alelo.consumer.consumerpat.entrypoint.advice;

import br.com.alelo.consumer.consumerpat.core.dto.error.ErrorMessageDto;
import br.com.alelo.consumer.consumerpat.core.dto.error.ErrorResponseDto;
import br.com.alelo.consumer.consumerpat.core.exception.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Slf4j
@RestControllerAdvice
public class RestAdvice {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponseDto> badRequest(BadRequestException exception) {

        List<ErrorMessageDto> errorMessageDtoList = new ArrayList<>();

        exception.getFieldsAndMessages().forEach((key, value) -> {
            Object[] args = new Object[1];
            args[0] = key;

            String message = this.messageSource.getMessage(value, args, Locale.ROOT);

            errorMessageDtoList.add(ErrorMessageDto.builder()
                    .message(message)
                    .code(value)
                    .build());
        });

        ErrorResponseDto errorResponseDto = ErrorResponseDto.builder().errors(errorMessageDtoList).build();
        return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> internalServerError(Exception exception) {
        log.info("m=internalServerError, message={}, cause={}", exception.getMessage(), exception.getCause());

        String code = "error.server";
        String message = this.messageSource.getMessage(code, null, Locale.getDefault());

        List<ErrorMessageDto> errorMessageDtoList = new ArrayList<>();
        errorMessageDtoList.add(ErrorMessageDto.builder()
                .message(message)
                .code(code)
                .build());

        ErrorResponseDto errorResponseDto = ErrorResponseDto.builder().errors(errorMessageDtoList).build();
        return new ResponseEntity<>(errorResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
