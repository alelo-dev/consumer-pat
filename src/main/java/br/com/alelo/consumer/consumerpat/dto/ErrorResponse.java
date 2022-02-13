package br.com.alelo.consumer.consumerpat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.util.Pair;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

@Data
public class ErrorResponse {

    private String message;
    private HashMap<String, Object> additionalInformation;

    public ErrorResponse(String message){
        this.message = message;
    }

    public ErrorResponse(String message, Pair<String, Object>... additionalInformation){
        this(message);

        if(this.additionalInformation == null)
            this.additionalInformation = new HashMap();

        Arrays.stream(additionalInformation).forEach(a -> this.additionalInformation.put(a.getFirst(), a.getSecond()));
    }

}
