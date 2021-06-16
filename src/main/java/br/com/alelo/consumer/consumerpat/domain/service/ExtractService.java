package br.com.alelo.consumer.consumerpat.domain.service;

import java.time.LocalDate;

public interface ExtractService {

    void saveExtract(String establishmentName, String productDescription, LocalDate date, int cardNumber, double value);

}
