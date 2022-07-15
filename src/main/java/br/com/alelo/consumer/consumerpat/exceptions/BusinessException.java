package br.com.alelo.consumer.consumerpat.exceptions;

import java.util.function.Supplier;

/**
 * @author renanravelli
 */
public class BusinessException extends RuntimeException {

  public BusinessException(String message) {
    super(message);
  }

  public static Supplier<BusinessException> supplier(String msg) {
    return () -> new BusinessException(msg);
  }
}
