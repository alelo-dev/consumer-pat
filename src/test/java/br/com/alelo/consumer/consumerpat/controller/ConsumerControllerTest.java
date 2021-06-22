package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = ConsumerController.class)
class ConsumerControllerTest {

    @Autowired
    ConsumerController controller;

    @MockBean
    ConsumerService service;

    @Test
    void getConsumersShouldGet204AndNoBody() {
        when(service.findAll(any())).thenReturn(Page.empty());

        ResponseEntity<Page<Consumer>> response = controller.getConsumers(Pageable.unpaged());

        assertEquals(204, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @Test
    void getConsumersShouldGet200AndBody() {
        List<Consumer> consumers = Arrays.asList(new Consumer(), new Consumer());
        Page<Consumer> page = new PageImpl<>(consumers, Pageable.unpaged(), 2L);
        when(service.findAll(any())).thenReturn(page);

        ResponseEntity<Page<Consumer>> response = controller.getConsumers(Pageable.unpaged());

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(page, response.getBody());
    }
}