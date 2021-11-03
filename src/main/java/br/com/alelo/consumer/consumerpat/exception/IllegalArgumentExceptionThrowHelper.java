package br.com.alelo.consumer.consumerpat.exception;

/**
 * Validacao de argumentos na construcao de objetos.
 *
 */
public final class IllegalArgumentExceptionThrowHelper {

	private IllegalArgumentExceptionThrowHelper() {}
	
	public static void throwIfMissingRequiredIllegalArgumentException(String argumentName, Object argumentValue) {
		if (argumentName == null) {
			throw new IllegalArgumentException(String.format("O argumento obrigatório %s não foi informado.", argumentName));
		}
	}
	
}
