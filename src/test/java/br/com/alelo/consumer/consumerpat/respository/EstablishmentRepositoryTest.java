package br.com.alelo.consumer.consumerpat.respository;

import br.com.alelo.consumer.consumerpat.entity.Establishment;
import br.com.alelo.consumer.consumerpat.utils.types.CardAndEstablishmentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static br.com.alelo.consumer.consumerpat.utils.types.CardAndEstablishmentType.DRUGSTORE;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class EstablishmentRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EstablishmentRepository establishmentRepository;

    @Test
    void findEstablishmentByNameAndType() {

        final String name = "test";
        final CardAndEstablishmentType type = DRUGSTORE;
        final Establishment establishment = Establishment.builder().name(name).type(type).build();
        entityManager.persist(establishment);

        final Optional<Establishment> response = establishmentRepository.findEstablishmentByNameAndType(name,
                type.name());

        assertTrue(response.isPresent());
        assertNotNull(response.get().getId());
    }
}