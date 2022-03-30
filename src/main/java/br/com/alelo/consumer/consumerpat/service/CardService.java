package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.exception.CardException;
import br.com.alelo.consumer.consumerpat.exception.EstablishmentTypeException;

import java.math.BigDecimal;

public interface CardService {

     void setBalance(Long cardNumber, BigDecimal value) throws CardException;
     void buy(Long establishmentType, String establishmentName, Long cardNumber, String productDescription, BigDecimal value) throws CardException, EstablishmentTypeException;
}
