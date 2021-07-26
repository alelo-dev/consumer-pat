package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.business.service.AuthBService;
import br.com.alelo.consumer.consumerpat.mock.AppMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 25/07/2021 | 20:58
 */

@ExtendWith(SpringExtension.class)
class AuthControllerTest {

    @InjectMocks
    private AuthController authController;
    @Mock
    private AuthBService authBService;

    @Test
    void generate() {
        when(this.authBService.save()).thenReturn(AppMock.mockAuthToken());
        Assertions.assertEquals(HttpStatus.OK, this.authController.generate().getStatusCode());
    }

    @Test
    void errorMissingToken() {
        Assertions.assertEquals("Missing header token", Objects.requireNonNull(this.authController.error("MISSING_TOKEN").getBody()).getDetails());
    }

    @Test
    void errorTokenExpired() {
        Assertions.assertEquals("Token expired", Objects.requireNonNull(this.authController.error("TOKEN_EXPIRED").getBody()).getDetails());
    }
}