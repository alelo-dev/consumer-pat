package br.com.alelo.consumerpat.core.usecase.impl;

import br.com.alelo.consumerpat.core.exception.ForbiddenException;
import br.com.alelo.consumerpat.core.usecase.AuthorizationTokenUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Slf4j
@Service
public class AuthorizationTokenUseCaseImpl implements AuthorizationTokenUseCase {

    private static final String tokenFixed = "B3E2E0C2409FEB9A8108C3EC92B40CA0";

    @Override
    public void validateToken(String token) throws ForbiddenException, NoSuchAlgorithmException {
        if (token == null || !token.matches("^Bearer .+")) {
            throw new ForbiddenException();
        }

        try {
            token = token.substring(7);

            AuthorizationTokenUseCaseImpl.isTokenValid(token);
        } catch (Exception ex) {
            log.info("m=validateToken, message={}", ex.getMessage());
            throw new ForbiddenException();
        }
    }

    private static void isTokenValid(String token) throws ForbiddenException {
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String value = new String(decoder.decode(token));

        if (!tokenFixed.equals(value)) {
            throw new ForbiddenException();
        }
    }
}
