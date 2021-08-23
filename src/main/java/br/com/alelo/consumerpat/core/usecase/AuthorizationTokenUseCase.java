package br.com.alelo.consumerpat.core.usecase;

import br.com.alelo.consumerpat.core.exception.ForbiddenException;

import java.security.NoSuchAlgorithmException;

public interface AuthorizationTokenUseCase {

    void validateToken(String token) throws ForbiddenException, NoSuchAlgorithmException;
}
