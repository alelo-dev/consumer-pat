package br.com.alelo.consumer.consumerpat.business.service;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 25/07/2021 | 11:28
 */

import br.com.alelo.consumer.consumerpat.entity.AuthToken;
import br.com.alelo.consumer.consumerpat.respository.AuthTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AuthBService {

    @Autowired
    private AuthTokenRepository authTokenRepository;

    public AuthToken save(){
        AuthToken authToken = new AuthToken();
        authToken.setToken(UUID.randomUUID().toString().replaceAll("-", ""));
        authToken.setCreatedAt(LocalDateTime.now());
        return this.authTokenRepository.save(authToken);
    }

}
