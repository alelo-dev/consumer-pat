package br.com.alelo.consumer.consumerpat.domain.service;

public interface Strategy {

    public Double creditValue(Double balance, Double value);

    public Double applyCashback(Double balance, Double value);
}
