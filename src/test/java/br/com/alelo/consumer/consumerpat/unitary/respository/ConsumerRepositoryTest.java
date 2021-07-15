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
        Consumer consumer = new Consumer();
        consumer.setName("Fulano");
        consumer.setDocumentNumber(12);

        repository.save(consumer);

        assertNotNull(consumer.getId());
    }

    @Test
    public void testFindByConsumerDocumentNumber() {
        Consumer consumer = new Consumer();
        consumer.setName("Fulano");
        consumer.setDocumentNumber(21);

        repository.save(consumer);

        Consumer consumerByDocument = repository.findByDocumentNumber(consumer.getDocumentNumber());

        assertNotNull(consumerByDocument);
    }

    @Test
    public void testDeleteConsumer() {
        Consumer consumer = new Consumer();
        consumer.setName("Fulano");
        consumer.setDocumentNumber(21);

        repository.save(consumer);
        repository.delete(consumer);

        Consumer consumerByDocument = repository.findByDocumentNumber(consumer.getDocumentNumber());

        assertNull(consumerByDocument);
    }
}