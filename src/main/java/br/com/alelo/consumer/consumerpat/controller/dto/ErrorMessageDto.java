package br.com.alelo.consumer.consumerpat.controller.dto;

import java.time.Instant;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ErrorMessageDto {
    
    private int     status;
    private Instant timestamp;
    private String  message;
    private String  client;
    
}