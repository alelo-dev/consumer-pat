package br.com.alelo.consumer.consumerpat.business.service;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.exception.ConsumerCreationException;
import br.com.alelo.consumer.consumerpat.exception.ConsumerRecoverCustomersException;
import br.com.alelo.consumer.consumerpat.mock.AppMock;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;


/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 25/07/2021 | 12:07
 */

@ExtendWith(SpringExtension.class)
class ConsumerBServiceTest {

    @InjectMocks
    private ConsumerBService consumerBService;
    @Mock
    private ConsumerRepository consumerRepository;

    @Test
    void recoverAllCustomersSuccess() {
        Page<Consumer> page = new PageImpl<>(AppMock.mockConsumers());
        Pageable pageable = PageRequest.of(0, 500, Sort.by("name"));
        when(this.consumerRepository.findAll(Mockito.any(Pageable.class))).thenReturn(page);
        Assertions.assertEquals(this.consumerBService.recoverAllCustomers(pageable).getContent().size(), AppMock.mockConsumers().size());
    }

    @Test
    void recoverAllCustomersError() {
        when(this.consumerRepository.findAll(Mockito.any(Pageable.class))).thenThrow(NullPointerException.class);
        Assertions.assertThrows(ConsumerRecoverCustomersException.class, () -> {
            Pageable pageable = PageRequest.of(0, 500, Sort.by("name"));
            this.consumerBService.recoverAllCustomers(pageable);
        });
    }

    @Test
    void saveSuccess() {
        when(this.consumerRepository.save(Mockito.any(Consumer.class))).thenReturn(
                new Consumer(1, "Usuario A", "123456", LocalDate.of(1990,1,1), LocalDateTime.now())
        );
        Assertions.assertEquals("123456", this.consumerBService.save(new Consumer()).getDocumentNumber());
    }

    @Test
    void saveError() {
        when(this.consumerRepository.save(Mockito.any(Consumer.class))).thenThrow(NullPointerException.class);
        Assertions.assertThrows(ConsumerCreationException.class, () -> {
            this.consumerBService.save(new Consumer());
        });
    }

    @Test
    void recoverByIdSuccess() {
        when(this.consumerRepository.findById(Mockito.any(Integer.class))).thenReturn(
                Optional.of(new Consumer(1, "Usuario A", "123456", LocalDate.of(1990,1,1), LocalDateTime.now()))
        );
        Assertions.assertEquals("123456", this.consumerBService.recoverById(1).getDocumentNumber());
    }

    @Test
    void recoverByIdError() {
        when(this.consumerRepository.findById(Mockito.any(Integer.class))).thenThrow(NullPointerException.class);
        Assertions.assertThrows(ConsumerRecoverCustomersException.class, () -> {
            this.consumerBService.recoverById(1);
        });
    }

    @Test
    void recoverByDocumentNumberSuccess() {
        when(this.consumerRepository.findByDocumentNumber(Mockito.any(String.class))).thenReturn(
                Optional.of(new Consumer(1, "Usuario A", "123456", LocalDate.of(1990,1,1), LocalDateTime.now()))
        );
        Assertions.assertEquals("123456", this.consumerBService.recoverByDocumentNumber("123").getDocumentNumber());
    }

    @Test
    void recoverByDocumentNumberError() {
        when(this.consumerRepository.findByDocumentNumber(Mockito.any(String.class))).thenThrow(NullPointerException.class);
        Assertions.assertThrows(ConsumerRecoverCustomersException.class, () -> {
            this.consumerBService.recoverByDocumentNumber("123");
        });
    }

}