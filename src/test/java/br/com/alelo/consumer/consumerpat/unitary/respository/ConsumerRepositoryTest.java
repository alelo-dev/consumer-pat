package br.com.alelo.consumer.consumerpat.unitary.respository;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
class ConsumerRepositoryTest {

    @Autowired
    ConsumerRepository repository;

    @Test
    public void testSaveConsumer() {
        Consumer consumer = Consumer.builder()
                .name("Fulano")
                .documentNumber(12)
                .build();

        repository.save(consumer);

        assertNotNull(consumer.getId());
    }

    @Test
    public void testFindByConsumerDocumentNumber() {
        Consumer consumer = Consumer.builder()
                .name("Fulano")
                .documentNumber(21)
                .build();

        repository.save(consumer);

        Consumer consumerByDocument = repository.findByDocumentNumber(consumer.getDocumentNumber());

        assertNotNull(consumerByDocument);
    }

    @Test
    public void testDeleteConsumer() {
        Consumer consumer = Consumer.builder()
                .name("Fulano")
                .documentNumber(21)
                .build();

        repository.save(consumer);
        repository.delete(consumer);

        Consumer consumerByDocument = repository.findByDocumentNumber(consumer.getDocumentNumber());

        assertNull(consumerByDocument);
    }
}