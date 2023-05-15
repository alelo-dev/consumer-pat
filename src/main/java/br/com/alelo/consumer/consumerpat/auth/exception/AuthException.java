package br.com.alelo.consumer.consumerpat.auth.execeptions;

public class AuthException extends RuntimeException {
	AuthException(String message) {
		super(message);
	}
}