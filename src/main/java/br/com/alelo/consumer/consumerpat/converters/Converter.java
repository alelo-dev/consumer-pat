package br.com.alelo.consumer.consumerpat.converters;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class Converter {

    private static final Converter INSTANCE = new Converter();

    private Converter() {
        super();
    }

    public static Converter getInstance() {
        return INSTANCE;
    }

    public <S, T> T converter(S source, Class<T> target) {
        BeanUtils.copyProperties(source, target);
        return (T) target;
    }

}