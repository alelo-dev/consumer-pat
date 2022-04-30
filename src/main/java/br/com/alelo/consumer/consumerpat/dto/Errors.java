package br.com.alelo.consumer.consumerpat.dto;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public class Errors {
    @Getter
    private List<String> errors;

    public Errors(String mensagemErro){
        this.errors = Arrays.asList(mensagemErro);
    }
}
