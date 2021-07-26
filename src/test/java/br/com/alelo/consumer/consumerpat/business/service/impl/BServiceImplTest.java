package br.com.alelo.consumer.consumerpat.business.service.impl;

import br.com.alelo.consumer.consumerpat.respository.ConsumerAddressRepository;
import br.com.alelo.consumer.consumerpat.respository.ConsumerAleloCardRepository;
import br.com.alelo.consumer.consumerpat.respository.ConsumerContactRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;


/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 25/07/2021 | 12:37
 */

@ExtendWith(SpringExtension.class)
class BServiceImplTest {

    @Mock
    private ConsumerAddressRepository consumerAddressRepository;
    @Mock
    private ConsumerContactRepository consumerContactRepository;
    @Mock
    private ConsumerAleloCardRepository consumerAleloCardRepository;
    @InjectMocks
    private AddressBServiceImpl addressBService;
    @InjectMocks
    private AleloCardBServiceImpl aleloCardBService;
    @InjectMocks
    private ContactBServiceImpl contactBService;

    @Test
    void testImpl(){
        Assertions.assertNotNull(this.addressBService);
        Assertions.assertNotNull(this.aleloCardBService);
        Assertions.assertNotNull(this.contactBService);
    }

}