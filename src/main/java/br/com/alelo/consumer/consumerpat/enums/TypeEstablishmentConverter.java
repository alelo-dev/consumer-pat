package br.com.alelo.consumer.consumerpat.enums;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TypeEstablishmentConverter implements Converter<Integer, TypeEstablishment> {

    @Override
    public TypeEstablishment convert(Integer value) {
        return TypeEstablishment.of(value);
    }
}