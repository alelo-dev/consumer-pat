package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Address;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Contact;
import br.com.alelo.consumer.consumerpat.repository.ConsumerRepository;
import org.assertj.core.util.Sets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@SuppressWarnings("squid:S5778")
class ConsumerServiceTest {

    @MockBean
    private ConsumerRepository consumerRepository;

    @Autowired
    private ConsumerService consumerService;

    private Consumer consumer;

    @BeforeEach
    void setup() {
        consumer = getConsumerByIdRandom();

        when(consumerRepository.save(any())).thenReturn(consumer);
    }

    private Consumer getConsumerByIdRandom() {
        return Consumer.builder()
                .id(new Random().nextLong())
                .name("Test")
                .birthDate(LocalDate.now())
                .documentNumber(new Random().nextInt())
                .cards(Sets.newHashSet())
                .address(Address.builder().build())
                .contact(Contact.builder().build())
                .build();
    }

    void assertConsumerIsValid(final Consumer consumer) {
        assertNotNull(consumer);
        assertNotNull(consumer.getName());
    }

    @Test
    void Find_all_when_its_call_page(){
        List<Consumer> consumerList = Arrays.asList(getConsumerByIdRandom(), getConsumerByIdRandom());
        when(consumerRepository.findAll(PageRequest.of(1, 500))).thenReturn(new PageImpl<>(consumerList));

        List<Consumer> consumersPage = consumerService.listAllConsumers(PageRequest.of(1, 500));

        assertEquals(consumerList.size(), consumersPage.size());
    }

    @Test
    void Find_all_when_its_call_page_with_different_value_returning_empty(){
        List<Consumer> consumerList = Arrays.asList(getConsumerByIdRandom(), getConsumerByIdRandom());
        when(consumerRepository.findAll(PageRequest.of(1, 500))).thenReturn(new PageImpl<>(consumerList));

        List<Consumer> consumersPage = consumerService.listAllConsumers(PageRequest.of(1, 501));

        assertTrue(consumersPage.isEmpty());
    }

    @Test
    void Find_all_when_its_call_page_with_different_value(){
        when(consumerRepository.findAll(PageRequest.of(1, 500))).thenThrow(RuntimeException.class);

        assertThrows(ResponseStatusException.class, () -> consumerService.listAllConsumers(PageRequest.of(1, 500)));
    }

    @Test
    void Update_when_consumer_has_problems(){
        when(consumerRepository.findById(any())).thenThrow(ResponseStatusException.class);
        assertThrows(ResponseStatusException.class, () -> consumerService.update(consumer));
    }

    @Test
    void Save_when_consumer_is_ok(){
        final Consumer saveConsumer = consumerService.save(Consumer.builder().build());
        assertConsumerIsValid(saveConsumer);
    }

    @Test
    void Save_when_consumer_has_problems(){
        when(consumerRepository.save(any())).thenThrow(ResponseStatusException.class);
        assertThrows(ResponseStatusException.class, () -> consumerService.save(consumer));
    }
}
