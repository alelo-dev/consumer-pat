package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Address;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Contact;
import br.com.alelo.consumer.consumerpat.repository.IConsumerRepository;
import br.com.alelo.consumer.consumerpat.service.validator.ConsumerValidator;
import org.assertj.core.util.Sets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
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
class ConsumerServiceTest {

    @Autowired
    private IConsumerService service;

    @MockBean
    private IConsumerRepository repository;

    @MockBean
    private ConsumerValidator validator;

    private static final String NAME = "NAME_CONSUMER";

    private Consumer consumer;

    @BeforeEach
    void setup() {
        consumer = getConsumerByIdRandom();

        when(repository.save(any())).thenReturn(consumer);
    }

    @Test
    void save_whenConsumerOk_thenReturnSuccess(){
        final Consumer saveConsumer = service.save(Consumer.builder().build());
        assertConsumerValid(saveConsumer);
    }

    @Test
    void save_whenConsumerWithProblem_thenResponseStatusException(){
        when(repository.save(any())).thenThrow(ResponseStatusException.class);
        assertThrows(ResponseStatusException.class, () -> service.save(consumer));
    }

    @Test
    void update_whenConsumerOk_thenReturnSuccess(){
        final Consumer saveConsumer = service.update(Consumer.builder().build());
        assertConsumerValid(saveConsumer);
    }

    @Test
    void update_whenConsumerWithProblemFind_thenResponseStatusException(){
        when(repository.findById(any())).thenThrow(ResponseStatusException.class);
        assertThrows(ResponseStatusException.class, () -> service.update(consumer));
    }

    @Test
    void findAll_whenCallPage_thenReturnSuccess(){
        List<Consumer> consumerList = Arrays.asList(getConsumerByIdRandom(), getConsumerByIdRandom());
        when(repository.findAll(PageRequest.of(1, 10))).thenReturn(new PageImpl<>(consumerList));

        Page<Consumer> consumersPage = service.findAll(PageRequest.of(1, 10));

        assertEquals(consumerList.size(), consumersPage.getTotalElements());
    }

    @Test
    void findAll_whenCallPageWithValueDifferent_thenReturnNull(){
        List<Consumer> consumerList = Arrays.asList(getConsumerByIdRandom(), getConsumerByIdRandom());
        when(repository.findAll(PageRequest.of(1, 10))).thenReturn(new PageImpl<>(consumerList));

        Page<Consumer> consumersPage = service.findAll(PageRequest.of(1, 11));

        assertNull(consumersPage);
    }

    @Test
    void findAll_whenCallPageWithValueDifferent_thenResponseStatusException(){
        when(repository.findAll(PageRequest.of(1, 10))).thenThrow(RuntimeException.class);

        assertThrows(ResponseStatusException.class, () -> service.findAll(PageRequest.of(1, 10)));
    }

    private void assertConsumerValid(final Consumer consumer) {
        assertNotNull(consumer);
        assertNotNull(consumer.getName());
    }

    private Consumer getConsumerByIdRandom() {
        return Consumer.builder()
                .id(new Random().nextLong())
                .name(NAME)
                .birthDate(LocalDate.now())
                .documentNumber(new Random().nextInt())
                .cards(Sets.newHashSet())
                .address(Address.builder().build())
                .contact(Contact.builder().build())
                .build();
    }
}
