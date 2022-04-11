package br.com.alelo.consumer.consumerpat.services.mapper;

public interface EntityMapper <A,B> {
    B map(A input);
}
