package br.com.alelo.consumer.consumerpat.utils.exception;

public class ProcessException extends Exception {

  public ProcessException(String message) {
    super(message);
  }

  public ProcessException(String message, Throwable err) {
    super(message, err);
  }
}
