package br.com.alelo.consumer.consumerpat.exceptions;

import java.util.function.Supplier;

/**
 * @author renanravelli
 */
public class NotFoundException extends RuntimeException {

  public NotFoundException(String message) {
    super(message);
  }

  public static Supplier<NotFoundException> supplier(String msg) {
    return () -> new NotFoundException(msg);
  }
}
