package br.com.alelo.consumer.consumerpat.respository;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class ConsumerRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ConsumerRepository consumerRepository;

    @Test
    void findConsumerByDocumentNumber() {

        final String documentName = "123456";
        final Consumer consumer = Consumer.builder().name("teste").documentNumber(documentName).build();
        entityManager.persist(consumer);

        final Optional<Consumer> response = consumerRepository.findConsumerByDocumentNumber(documentName);

        assertTrue(response.isPresent());
        assertNotNull(response.get().getId());
    }
}