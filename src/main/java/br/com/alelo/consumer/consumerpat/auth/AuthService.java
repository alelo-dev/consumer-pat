package br.com.alelo.consumer.consumerpat.auth;

import br.com.alelo.consumer.consumerpat.auth.execeptions.AuthException;
import org.springframework.http.ResponseEntity;

public interface AuthService {

	ResponseEntity<String> logout() throws AuthException;

}