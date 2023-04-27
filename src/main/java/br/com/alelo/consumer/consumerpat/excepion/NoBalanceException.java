package br.com.alelo.consumer.consumerpat.excepion;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.EXPECTATION_FAILED)
public class NoBalanceException extends RuntimeException {
}
