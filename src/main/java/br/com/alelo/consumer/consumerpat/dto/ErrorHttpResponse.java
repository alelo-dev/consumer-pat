package br.com.alelo.consumer.consumerpat.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorHttpResponse {
    private String userMessage;
    private String developerMessage;
}
