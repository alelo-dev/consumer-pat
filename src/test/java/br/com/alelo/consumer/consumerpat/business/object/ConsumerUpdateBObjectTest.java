package br.com.alelo.consumer.consumerpat.business.object;

import br.com.alelo.consumer.consumerpat.business.service.ConsumerBService;
import br.com.alelo.consumer.consumerpat.business.service.impl.AddressBServiceImpl;
import br.com.alelo.consumer.consumerpat.business.service.impl.AleloCardBServiceImpl;
import br.com.alelo.consumer.consumerpat.business.service.impl.ContactBServiceImpl;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.ConsumerAddress;
import br.com.alelo.consumer.consumerpat.entity.ConsumerAleloCard;
import br.com.alelo.consumer.consumerpat.entity.ConsumerContact;
import br.com.alelo.consumer.consumerpat.exception.ConsumerRecoverCustomersException;
import br.com.alelo.consumer.consumerpat.exception.ConsumerRecoverNotFoundException;
import br.com.alelo.consumer.consumerpat.mock.AppMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 25/07/2021 | 15:12
 */

@ExtendWith({SpringExtension.class})
class ConsumerUpdateBObjectTest {

    @InjectMocks
    private ConsumerUpdateBObject consumerUpdateBObject;
    @Mock
    private ConsumerBService consumerBService;
    @Mock
    private ContactBServiceImpl contactBServiceImpl;
    @Mock
    private AddressBServiceImpl addressBServiceImpl;
    @Mock
    private AleloCardBServiceImpl aleloCardBService;

    @Test
    void prepareToUpdateCustomerSuccess() {
        when(this.consumerBService.recoverByDocumentNumber(Mockito.anyString())).thenReturn(AppMock.consumerMock());
        when(this.consumerBService.save(Mockito.any(Consumer.class))).thenReturn(AppMock.consumerMock());
        when(this.aleloCardBService.recoverByCardNumber(Mockito.anyString())).thenReturn(null);
        when(this.addressBServiceImpl.recoverById(Mockito.anyInt())).thenReturn(null);
        when(this.contactBServiceImpl.recoverById(Mockito.anyInt())).thenReturn(null);
        when(this.contactBServiceImpl.save(Mockito.any(ConsumerContact.class))).thenReturn(AppMock.contactMock());
        when(this.addressBServiceImpl.save(Mockito.any(ConsumerAddress.class))).thenReturn(AppMock.addressMock());
        when(this.aleloCardBService.save(Mockito.any(ConsumerAleloCard.class))).thenReturn(AppMock.cardMock());
        Assertions.assertEquals(HttpStatus.OK, this.consumerUpdateBObject.prepareToUpdateCustomer(AppMock.consumerDTOMock()).getStatusCode());
    }

    @Test
    void prepareToUpdateCustomerNotFound() {
        when(this.consumerBService.recoverByDocumentNumber(Mockito.anyString())).thenReturn(null);
        Assertions.assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, this.consumerUpdateBObject.prepareToUpdateCustomer(AppMock.consumerDTOMock()).getStatusCode());
    }

    @Test
    void prepareToUpdateCustomerNotFoundError() {
        when(this.consumerBService.recoverByDocumentNumber(Mockito.anyString())).thenThrow(ConsumerRecoverNotFoundException.class);
        Assertions.assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, this.consumerUpdateBObject.prepareToUpdateCustomer(AppMock.consumerDTOMock()).getStatusCode());
    }

    @Test
    void prepareToUpdateCustomerRecoverError() {
        when(this.consumerBService.recoverByDocumentNumber(Mockito.anyString())).thenThrow(ConsumerRecoverCustomersException.class);
        Assertions.assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, this.consumerUpdateBObject.prepareToUpdateCustomer(AppMock.consumerDTOMock()).getStatusCode());
    }
}