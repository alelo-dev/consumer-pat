package br.com.alelo.consumer.consumerpat.exception;

import br.com.alelo.consumer.consumerpat.error.BusinessError;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final BusinessError businessError;

}
