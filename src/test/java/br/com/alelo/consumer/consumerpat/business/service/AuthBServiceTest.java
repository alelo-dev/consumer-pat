package br.com.alelo.consumer.consumerpat.business.service;

import br.com.alelo.consumer.consumerpat.entity.AuthToken;
import br.com.alelo.consumer.consumerpat.mock.AppMock;
import br.com.alelo.consumer.consumerpat.respository.AuthTokenRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.mockito.Mockito.when;


/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 25/07/2021 | 12:00
 */

@ExtendWith(SpringExtension.class)
class AuthBServiceTest {

    @InjectMocks
    private AuthBService authBService;
    @Mock
    private AuthTokenRepository authTokenRepository;

    @Test
    void save() {
        when(this.authTokenRepository.save(Mockito.any(AuthToken.class))).thenReturn(AppMock.mockAuthToken());
        Assertions.assertEquals(this.authBService.save(), AppMock.mockAuthToken());
    }


}