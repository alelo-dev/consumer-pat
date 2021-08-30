package br.com.alelo.consumer.consumerpat.controller.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class ControllerAdviceExceptionHandler {

    private final Logger looger = LoggerFactory.getLogger(ControllerAdviceExceptionHandler.class);

    
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<String> handleException(Exception e) {
        if(e != null  && e.getMessage() != null)
            looger.error(e.getMessage(),e);
        else
            looger.error("Erro generico",e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("error",e.getMessage()+" Código: ["+getTraceId()+"]").body(e.getMessage()+" Código: ["+getTraceId()+"]");
    }

    private String getTraceId(){
        return MDC.get("X-B3-TraceId");
    }

}
