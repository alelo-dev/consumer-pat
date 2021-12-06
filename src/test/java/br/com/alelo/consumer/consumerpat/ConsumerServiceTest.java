package br.com.alelo.consumer.consumerpat;

import br.com.alelo.consumer.consumerpat.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.mapping.ConsumerMapping;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ConsumerServiceTest {

    @Autowired
    private ConsumerService service;

    @Mock
    private ConsumerRepository repository;

    private ConsumerDTO consumerDTO;

    @BeforeEach
    public void setup() {
        consumerDTO = ConsumerDTO
                .builder()
                .birthDate(new Date())
                .name("Teste")
                .documentNumber(1234567)
                .mobilePhoneNumber(3131231)
                .residencePhoneNumber(1231231)
                .phoneNumber(1232131)
                .email("teste@teste.com")
                .foodCardNumber(123456789)
                .foodCardBalance(100.0)
                .fuelCardNumber(987654321)
                .fuelCardBalance(0.0)
                .drugstoreCardNumber(192837465)
                .drugstoreCardBalance(400.0)
                .street("Rua teste")
                .number(0)
                .city("Brasilia")
                .country("DF")
                .postalCode(72000000)
                .build();
        when(repository.save(Mockito.any(Consumer.class))).thenReturn(ConsumerMapping.to(consumerDTO));
    }

    @Test
    void shouldSaveConsumerSuccess() {
        var consumer = service.save(consumerDTO);
        assertEquals( "Teste", consumer.getName());
    }

}
