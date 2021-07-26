package br.com.alelo.consumer.consumerpat.business.object;

import br.com.alelo.consumer.consumerpat.business.service.impl.AleloCardBServiceImpl;
import br.com.alelo.consumer.consumerpat.entity.ConsumerAleloCard;
import br.com.alelo.consumer.consumerpat.mock.AppMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;


/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 25/07/2021 | 13:14
 */

@ExtendWith(SpringExtension.class)
class ConsumerCardChargeBObjectTest {

    @InjectMocks
    private ConsumerCardChargeBObject consumerCardChargeBObject;
    @Mock
    private AleloCardBServiceImpl aleloCardBService;

    @Test
    void prepareToChargeCardSuccess() {
        when(this.aleloCardBService.recoverByCardNumber(Mockito.anyString())).thenReturn(AppMock.cardMock());
        when(this.aleloCardBService.save(Mockito.any(ConsumerAleloCard.class))).thenReturn(AppMock.cardMock());
        Assertions.assertEquals(HttpStatus.OK.value(), this.consumerCardChargeBObject.prepareToChargeCard("123", new BigDecimal("45.3")).getStatusCode().value());
    }

    @Test
    void prepareToChargeCardNull() {
        when(this.aleloCardBService.recoverByCardNumber(Mockito.anyString())).thenReturn(null);
        Assertions.assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), this.consumerCardChargeBObject.prepareToChargeCard("123", new BigDecimal("45.3")).getStatusCode().value());
    }

    @Test
    void prepareToChargeCardError() {
        when(this.aleloCardBService.recoverByCardNumber(Mockito.anyString())).thenReturn(AppMock.cardMock());
        when(this.aleloCardBService.save(Mockito.any(ConsumerAleloCard.class))).thenThrow(NullPointerException.class);
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), this.consumerCardChargeBObject.prepareToChargeCard("123", new BigDecimal("45.3")).getStatusCode().value());
    }
}