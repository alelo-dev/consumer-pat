package br.com.alelo.consumer.consumerpat.exception;

public class InvalidCardException extends Exception {

  public InvalidCardException() {}

  public InvalidCardException(String message) {
    super(message);
  }
}
