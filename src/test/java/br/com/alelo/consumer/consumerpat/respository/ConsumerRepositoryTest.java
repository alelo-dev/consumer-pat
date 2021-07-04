package br.com.alelo.consumer.consumerpat.respository;

import br.com.alelo.consumer.consumerpat.domain.consumer.Address;
import br.com.alelo.consumer.consumerpat.domain.consumer.Consumer;
import br.com.alelo.consumer.consumerpat.domain.consumer.Contact;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
class ConsumerRepositoryTest {

    @Autowired
    TestEntityManager manager;

    @Autowired
    ConsumerRepository repository;
    @BeforeEach
    public void init(){
        repository.deleteAll();
        manager.clear();
    }

    @Test
    @DisplayName("Deve criar um consumer e confirmar os dados")
    public void returnTrueWhenConsumerExists(){

        final Consumer entity = createEntity("77733399900");
        final Consumer consumerSaved = manager.persist(entity);

        final Optional<Consumer> consumer= repository.findById(consumerSaved.getId());

        assertThat(consumer.isPresent()).isTrue();
        manager.clear();
    }
    @Test
    @DisplayName("Não deve permitir inserir dois CPFs iguais")
    public void returnExceptionWhenConsumerExists(){

        repository.deleteAll();
        manager.clear();
        final Consumer entity = createEntity("77532198200");

        repository.save(entity);
        repository.save(entity);
        final List<Consumer> all = repository.findAll();
        final Consumer consumer1 = all.get(0);
        assertThat(all.size()).isEqualTo(1);
       assertThat(consumer1.getId()).isNotNull();
       assertThat(consumer1.getAddress().getAddressId()).isNotNull();
//       assertThat(consumer1.getContact().getContactId()).isNotNull();

    }
    @Test
    @DisplayName("Inserir Lista de Cartões")
    public void insertDistinctsCards(){

        final Consumer entity = createEntityWithCards();
        repository.deleteAll();

        repository.save(entity);
        final List<Consumer> all = repository.findAll();

       assertThat(all.size()).isEqualTo(1);
       assertThat(all.get(0).getId()).isNotNull();
       assertThat(all.get(0).getAddress().getAddressId()).isNotNull();
//       assertThat(all.get(0).getContact().getContactId()).isNotNull();
//       assertThat(all.get(0).getCards().size()).isEqualTo(3);
       manager.clear();
    }

    private Consumer createEntityWithCards() {
        final Consumer consumer = Consumer.builder()
                .birthDate(LocalDate.of(1983, 10, 10))
                .documentNumber("77532198200")
                .contact(Contact.builder().build())
                .name("Pixel")
                .address(Address.builder().city("São Paulo").country("Brasil").build()).build();
//        consumer.addCard(new FuelCard("3333444555666", 300));
//        consumer.addCard(new FoodCard("1111222333444", 1000));
//        consumer.addCard(new DrugStoreCard("2222333444555", 500));
        return consumer;
    }

    private Consumer createEntity(final String document ) {
        return Consumer.builder()
                .birthDate(LocalDate.of(1983, 10, 10))
                .documentNumber(document)
                .contact(Contact.builder().build())
                .address(Address.builder().city("São Paulo").country("Brasil").build()).build();
    }

}