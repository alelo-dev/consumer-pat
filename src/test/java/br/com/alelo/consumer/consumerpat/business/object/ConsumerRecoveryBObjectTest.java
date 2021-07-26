package br.com.alelo.consumer.consumerpat.business.object;

import br.com.alelo.consumer.consumerpat.business.service.ConsumerBService;
import br.com.alelo.consumer.consumerpat.business.service.impl.AddressBServiceImpl;
import br.com.alelo.consumer.consumerpat.business.service.impl.AleloCardBServiceImpl;
import br.com.alelo.consumer.consumerpat.business.service.impl.ContactBServiceImpl;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.exception.ConsumerRecoverCustomersException;
import br.com.alelo.consumer.consumerpat.mock.AppMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 25/07/2021 | 14:44
 */

@ExtendWith(SpringExtension.class)
class ConsumerRecoveryBObjectTest {

    @InjectMocks
    private ConsumerRecoveryBObject consumerRecoveryBObject;
    @Mock
    private ConsumerBService consumerBService;
    @Mock
    private ContactBServiceImpl contactBServiceImpl;
    @Mock
    private AddressBServiceImpl addressBServiceImpl;
    @Mock
    private AleloCardBServiceImpl aleloCardBService;

    @Test
    void prepareToRecoverAllCustomersSuccess() {
        Page<Consumer> consumerPage = new PageImpl<>(AppMock.mockConsumers());
        when(this.consumerBService.recoverAllCustomers(Mockito.any(Pageable.class))).thenReturn(consumerPage);
        when(this.aleloCardBService.recoverAllByConsumer(Mockito.any(Consumer.class))).thenReturn(AppMock.cardsMock());
        when(this.addressBServiceImpl.recoverAllByConsumer(Mockito.any(Consumer.class))).thenReturn(AppMock.listAddressMock());
        when(this.contactBServiceImpl.recoverAllByConsumer(Mockito.any(Consumer.class))).thenReturn(AppMock.listContactsMock());
        Assertions.assertEquals(HttpStatus.OK, this.consumerRecoveryBObject.prepareToRecoverAllCustomers(null, null).getStatusCode());
        Assertions.assertEquals(HttpStatus.OK, this.consumerRecoveryBObject.prepareToRecoverAllCustomers(0, 500).getStatusCode());
    }

    @Test
    void prepareToRecoverAllCustomersRecoveryException(){
        Page<Consumer> consumerPage = new PageImpl<>(AppMock.mockConsumers());
        when(this.consumerBService.recoverAllCustomers(Mockito.any(Pageable.class))).thenThrow(ConsumerRecoverCustomersException.class);
        Assertions.assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, this.consumerRecoveryBObject.prepareToRecoverAllCustomers(null, null).getStatusCode());
    }

    @Test
    void prepareToRecoverAllCustomersException(){
        Page<Consumer> consumerPage = new PageImpl<>(AppMock.mockConsumers());
        when(this.consumerBService.recoverAllCustomers(Mockito.any(Pageable.class))).thenThrow(NullPointerException.class);
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, this.consumerRecoveryBObject.prepareToRecoverAllCustomers(null, null).getStatusCode());
    }
}