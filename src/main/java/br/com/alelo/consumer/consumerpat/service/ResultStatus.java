package br.com.alelo.consumer.consumerpat.service;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ResultStatus {
    private boolean isOk;
    private String error;
}
