package br.com.alelo.consumer.consumerpat.business.object;

import br.com.alelo.consumer.consumerpat.business.service.ExtractBService;
import br.com.alelo.consumer.consumerpat.business.service.impl.AleloCardBServiceImpl;
import br.com.alelo.consumer.consumerpat.entity.ConsumerAleloCard;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.mock.AppMock;
import br.com.alelo.consumer.consumerpat.util.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 25/07/2021 | 14:59
 */

@ExtendWith({SpringExtension.class})
class ExtractBObjectTest {

    @InjectMocks
    private ExtractBObject extractBObject;
    @Mock
    private ExtractBService extractBService;
    @Mock
    private AleloCardBServiceImpl aleloCardBService;

    @Test
    void prepareToExtractSuccess() {

        when(this.aleloCardBService.recoverByCardNumber(Mockito.anyString())).thenReturn(AppMock.cardMock());
        when(this.extractBService.save(Mockito.any(Extract.class))).thenReturn(AppMock.mockExtract());
        Assertions.assertEquals(
                HttpStatus.CREATED,
                this.extractBObject
                        .prepareToExtract(1, "nome", "123", "description", new BigDecimal("25.33"))
                        .getStatusCode());

    }

    @Test
    void prepareToExtractBalanceInsuficient(){
        ConsumerAleloCard aleloCard = AppMock.cardMock();
        aleloCard.setBalance(new BigDecimal("10.33"));
        when(this.aleloCardBService.recoverByCardNumber(Mockito.anyString())).thenReturn(aleloCard);
        when(this.extractBService.save(Mockito.any(Extract.class))).thenReturn(AppMock.mockExtract());
        Assertions.assertEquals(
                Constants.Errors.INSUFICIENT_BALANCE,
                Objects.requireNonNull(this.extractBObject
                        .prepareToExtract(1, "nome", "123", "description", new BigDecimal("25.33"))
                        .getBody()).getMessage());
    }

    @Test
    void prepareToExtractCardNotFound(){
        when(this.aleloCardBService.recoverByCardNumber(Mockito.anyString())).thenReturn(null);
        Assertions.assertEquals(
                HttpStatus.BAD_REQUEST,
                Objects.requireNonNull(this.extractBObject
                        .prepareToExtract(1, "nome", "123", "description", new BigDecimal("25.33"))
                        .getStatusCode()));
    }

    @Test
    void prepareToExtractError(){
        when(this.aleloCardBService.recoverByCardNumber(Mockito.anyString())).thenThrow(NullPointerException.class);
        Assertions.assertEquals(
                HttpStatus.INTERNAL_SERVER_ERROR,
                Objects.requireNonNull(this.extractBObject
                        .prepareToExtract(1, "nome", "123", "description", new BigDecimal("25.33"))
                        .getStatusCode()));
    }
}