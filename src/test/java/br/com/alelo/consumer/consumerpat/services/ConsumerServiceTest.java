package br.com.alelo.consumer.consumerpat.services;

import br.com.alelo.consumer.consumerpat.controller.request.ConsumerRequest;
import br.com.alelo.consumer.consumerpat.domain.consumer.Address;
import br.com.alelo.consumer.consumerpat.domain.consumer.Consumer;
import br.com.alelo.consumer.consumerpat.domain.consumer.Contact;
import br.com.alelo.consumer.consumerpat.infra.converter.ConsumerRequestToConsumerConverter;
import br.com.alelo.consumer.consumerpat.infra.converter.ConsumerToConsumerResponseConverter;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class ConsumerServiceTest {

    ConsumerService consumerService;

    @MockBean
    ConsumerRepository repository;
    @MockBean
    CardService cardService;
    @MockBean
    ConsumerToConsumerResponseConverter toConsumerResponse;
    @MockBean
    ConsumerRequestToConsumerConverter toConsumer;

    @BeforeEach
    public void setUp(){
        this.consumerService = new ConsumerService(repository, cardService, toConsumerResponse, toConsumer);
    }

    @Test
    @DisplayName("Deve atualizar um consumer")
    public void saveConsumer(){
        // cenário
        final ConsumerRequest build = ConsumerRequest.builder().name("andre 1").birthDate(LocalDate.of(1983, 10, 11)).build();

        final long id = 1l;
        final Consumer andre = Consumer.builder().name("Andre").birthDate(LocalDate.of(1983, 11, 11)).build();
        when(repository.findById(id)).thenReturn(Optional.of(andre));

        // execução

        consumerService.update(build, id);

        // verificação
        assertThat(andre.getName()).isEqualTo(build.getName());
        assertThat(andre.getBirthDate()).isEqualTo(build.getBirthDate());


    }

    private Consumer createEntity(final String document ) {
        return Consumer.builder()
                .birthDate(LocalDate.of(1983, 10, 10))
                .documentNumber(document)
                .contact(Contact.builder().build())
                .address(Address.builder().city("São Paulo").country("Brasil").build()).build();
    }

}