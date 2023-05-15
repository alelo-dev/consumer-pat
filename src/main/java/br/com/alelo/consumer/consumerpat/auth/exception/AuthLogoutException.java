package br.com.alelo.consumer.consumerpat.auth.execeptions;

public class AuthLogoutException extends AuthException {

	public AuthLogoutException(String exception) {
		super(exception);
	}

	public AuthLogoutException() {
		super("Logout error");
	}
}