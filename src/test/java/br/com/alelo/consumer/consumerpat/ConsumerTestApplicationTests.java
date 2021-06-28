package br.com.alelo.consumer.consumerpat;

import br.com.alelo.consumer.consumerpat.controller.ConsumerController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class ConsumerTestApplicationTests {
    @Autowired private ConsumerController controller;

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }
}
