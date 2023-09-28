package br.com.alelo.consumer.consumerpat.exception;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse implements Serializable{
    private static final long serialVersionUID = 1L;
	private String timeStamp;
	private Integer code;
    private HttpStatus httpStatus;
    private String message;
    private JsonNode details;

    public ErrorResponse(HttpStatus httpStatus, String message){
    
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        
        String formatedTimeStamp = new SimpleDateFormat("HH:mm:ss - dd/MM/yyyy ").format(timestamp);
        
        this.timeStamp = formatedTimeStamp;
        
        this.code = httpStatus.value();
        
        this.httpStatus = httpStatus;
        
        this.message = message;
        
    }

    
}
