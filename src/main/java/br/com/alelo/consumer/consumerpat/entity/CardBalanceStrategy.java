package br.com.alelo.consumer.consumerpat.entity;

public interface CardBalanceStrategy {
    double calculateTransactionValue(double value);
    boolean isEstablishmentAllowed(int establishmentId);
}
