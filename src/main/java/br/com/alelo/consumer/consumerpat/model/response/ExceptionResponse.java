package br.com.alelo.consumer.consumerpat.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {

    private Date timestamp;

    private Integer status;

    private String message;

    private String path;

    private String internalErrorCode;

}
