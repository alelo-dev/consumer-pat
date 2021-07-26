package br.com.alelo.consumer.consumerpat.business.service.impl;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.ConsumerAleloCard;
import br.com.alelo.consumer.consumerpat.exception.ConsumerContactCreationException;
import br.com.alelo.consumer.consumerpat.exception.ConsumerRecoverObjectCustomerException;
import br.com.alelo.consumer.consumerpat.mock.AppMock;
import br.com.alelo.consumer.consumerpat.respository.ConsumerAleloCardRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;


/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 25/07/2021 | 12:41
 */

@ExtendWith(SpringExtension.class)
class AleloCardBServiceImplTest {

    @InjectMocks
    private AleloCardBServiceImpl aleloCardBService;
    @Mock
    private ConsumerAleloCardRepository consumerAleloCardRepository;

    @Test
    void recoverByCardNumberSuccess() {
        when(this.consumerAleloCardRepository.findByNumber(Mockito.anyString())).thenReturn(Optional.of(AppMock.cardMock()));
        Assertions.assertEquals(this.aleloCardBService.recoverByCardNumber("123").getNumber(), AppMock.cardMock().getNumber());
    }

    @Test
    void recoverByCardNumberError() {
        when(this.consumerAleloCardRepository.findByNumber(Mockito.anyString())).thenThrow(NullPointerException.class);
        Assertions.assertThrows(ConsumerRecoverObjectCustomerException.class, () -> {
            this.aleloCardBService.recoverByCardNumber("123");
        });
    }

    @Test
    void saveSuccess(){
        when(this.consumerAleloCardRepository.save(Mockito.any(ConsumerAleloCard.class))).thenReturn(AppMock.cardMock());
        Assertions.assertEquals("123", this.aleloCardBService.save(new ConsumerAleloCard()).getNumber());
    }

    @Test
    void saveError(){
        when(this.consumerAleloCardRepository.save(Mockito.any(ConsumerAleloCard.class))).thenThrow(NullPointerException.class);
        Assertions.assertThrows(ConsumerContactCreationException.class, () -> {
            this.aleloCardBService.save(new ConsumerAleloCard());
        });
    }

    @Test
    void recoverAllByConsumerSuccess(){
        when(this.consumerAleloCardRepository.findAllByConsumer(Mockito.any(Consumer.class))).thenReturn(AppMock.cardsMock());
        Assertions.assertEquals(3, this.aleloCardBService.recoverAllByConsumer(new Consumer()).size());

    }

    @Test
    void recoverAllByConsumerError(){
        when(this.consumerAleloCardRepository.findAllByConsumer(Mockito.any(Consumer.class))).thenThrow(NullPointerException.class);
        Assertions.assertThrows(ConsumerRecoverObjectCustomerException.class, () -> {
            this.aleloCardBService.recoverAllByConsumer(new Consumer());
        });
    }


}