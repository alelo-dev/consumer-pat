package br.com.alelo.consumer.consumerpat.contants;

import lombok.Getter;

import java.util.regex.Pattern;

@Getter
public enum RegexDefault {
    NAME("^([a-zA-Z]{2,}\\s[a-zA-Z]{1,}'?-?[a-zA-Z]{2,}\\s?([a-zA-Z]{1,})?).*"),
    EMAIL("[a-z0-9!#$%&'*+\\/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+\\/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?");

    private String pattern;

    RegexDefault(String pattern){
        this.pattern = pattern;
    }

}
