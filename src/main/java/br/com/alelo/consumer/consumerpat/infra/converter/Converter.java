package br.com.alelo.consumer.consumerpat.infra.converter;

@FunctionalInterface
public interface Converter<T, D> {

    D convert(final T domain);

}
