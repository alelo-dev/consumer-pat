package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.business.object.ConsumerCardChargeBObject;
import br.com.alelo.consumer.consumerpat.business.object.ConsumerCreationBObject;
import br.com.alelo.consumer.consumerpat.business.object.ConsumerRecoveryBObject;
import br.com.alelo.consumer.consumerpat.business.object.ConsumerUpdateBObject;
import br.com.alelo.consumer.consumerpat.business.object.ExtractBObject;
import br.com.alelo.consumer.consumerpat.model.dto.ApiDTO;
import br.com.alelo.consumer.consumerpat.model.dto.consumer.ConsumerDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;


/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 25/07/2021 | 21:03
 */

@ExtendWith(SpringExtension.class)
class ConsumerControllerTest {

    @InjectMocks
    private ConsumerController consumerController;
    @Mock
    private ConsumerRecoveryBObject consumerRecoveryBObject;
    @Mock
    private ConsumerCreationBObject consumerCreationBObject;
    @Mock
    private ConsumerUpdateBObject consumerUpdateBObject;
    @Mock
    private ConsumerCardChargeBObject consumerCardChargeBObject;
    @Mock
    private ExtractBObject extractBObject;


    @Test
    void listAllConsumers() {
        when(this.consumerRecoveryBObject.prepareToRecoverAllCustomers(Mockito.anyInt(), Mockito.anyInt())).thenReturn(ResponseEntity.ok(new ApiDTO<>()));
        Assertions.assertEquals(HttpStatus.OK, this.consumerController.listAllConsumers(0, 25, "ABC").getStatusCode());
    }

    @Test
    void createConsumer() {
        when(this.consumerCreationBObject.prepareToSaveCustomer(Mockito.any(ConsumerDTO.class))).thenReturn(ResponseEntity.ok(new ApiDTO<>()));
        Assertions.assertEquals(HttpStatus.OK, this.consumerController.createConsumer(new ConsumerDTO(), "ABC").getStatusCode());
    }

    @Test
    void updateConsumer() {
        when(this.consumerUpdateBObject.prepareToUpdateCustomer(Mockito.any(ConsumerDTO.class))).thenReturn(ResponseEntity.ok(new ApiDTO<>()));
        Assertions.assertEquals(HttpStatus.OK, this.consumerController.updateConsumer(new ConsumerDTO(), "ABC").getStatusCode());
    }

    @Test
    void setBalance() {
        when(this.consumerCardChargeBObject.prepareToChargeCard(Mockito.anyString(), Mockito.any())).thenReturn(ResponseEntity.ok(new ApiDTO<>()));
        Assertions.assertEquals(HttpStatus.OK, this.consumerController.setBalance("", new BigDecimal("25.00"), "ABC").getStatusCode());
    }

    @Test
    void buy() {
        when(this.extractBObject.prepareToExtract(Mockito.anyInt(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.any()))
                .thenReturn(ResponseEntity.ok(new ApiDTO<>()));
        Assertions.assertEquals(HttpStatus.OK,
                this.consumerController.buy(0, "", "", "", new BigDecimal("25"), "").getStatusCode());
    }
}