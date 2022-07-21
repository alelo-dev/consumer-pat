package br.com.alelo.consumer.consumerpat;

import br.com.alelo.consumer.consumerpat.domain.entity.Consumer;
import br.com.alelo.consumer.consumerpat.domain.enumeration.CardType;
import br.com.alelo.consumer.consumerpat.domain.service.ConsumerService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ConsumerServiceTest {

    @Autowired
    private ConsumerService consumerService;

    private static Consumer consumer1;
    private static Consumer consumer2;
    private static Consumer consumer3;

    @BeforeAll
    public static void setup() {

        consumer1 = Consumer.builder()
                .name("Thiago Monteiro")
                .documentNumber(1234567l)
                .birthDate(new Date())
                .mobilePhoneNumber(62999999999l)
                .residencePhoneNumber(6233333333l)
                .phoneNumber(62999999999l)
                .email("thiago.monteiro@gmail.com")
                .street("Rua dos Nerds")
                .number(1024)
                .city("Goiânia")
                .country("Brasil")
                .portalCode(123)
                .foodCardNumber(BigInteger.valueOf(12344l))
                .foodCardBalance(BigDecimal.valueOf(205.55))
                .fuelCardNumber(BigInteger.valueOf(12345l))
                .fuelCardBalance(BigDecimal.valueOf(200.0))
                .drugstoreCardNumber(BigInteger.valueOf(12346l))
                .drugstoreCardBalance(BigDecimal.valueOf(158.99))
                .build();

        consumer2 = Consumer.builder()
                .name("José Afonso dos Santos")
                .documentNumber(2244669l)
                .birthDate(new Date())
                .mobilePhoneNumber(21999999999l)
                .residencePhoneNumber(2133333344l)
                .phoneNumber(21999999977l)
                .email("joseas@gmail.com")
                .street("Barão do Rio Branco")
                .number(4096)
                .city("Rio de Janeiro")
                .country("Brasil")
                .portalCode(2121)
                .foodCardNumber(BigInteger.valueOf(12335l))
                .foodCardBalance(BigDecimal.valueOf(300.0))
                .fuelCardNumber(BigInteger.valueOf(12125l))
                .fuelCardBalance(BigDecimal.valueOf(300.0))
                .drugstoreCardNumber(BigInteger.valueOf(11336l))
                .drugstoreCardBalance(BigDecimal.valueOf(300.0))
                .build();

        consumer3 = Consumer.builder()
                .name("Ana Maria Bastos")
                .documentNumber(5522760l)
                .birthDate(new Date())
                .mobilePhoneNumber(62999998888l)
                .residencePhoneNumber(6233333152l)
                .phoneNumber(62999992275l)
                .email("anamaria.bastos@gmail.com")
                .street("Av. Manuel da Abadia")
                .number(4096)
                .city("Anápolis")
                .country("Brasil")
                .portalCode(5775)
                .foodCardNumber(BigInteger.valueOf(22345l))
                .foodCardBalance(BigDecimal.valueOf(400.0))
                .fuelCardNumber(BigInteger.valueOf(22525l))
                .fuelCardBalance(BigDecimal.valueOf(300.0))
                .drugstoreCardNumber(BigInteger.valueOf(51536l))
                .drugstoreCardBalance(BigDecimal.valueOf(300.0))
                .build();
    }


    @Test
    void testSaveConsumer() {
        consumerService.saveConsumer(consumer1);
        Assertions.assertNotNull(consumer1.getId());
    }

    @Test
    public void testFindConsumerByFoodCardNumber() {

        Consumer expectedConsumer = consumer1;

        Consumer actualConsumer = consumerService.findConsumerByCardNumber(
                consumer1.getFoodCardNumber(), CardType.FOOD_CARD);

        Assertions.assertEquals(expectedConsumer.getFoodCardNumber(), actualConsumer.getFoodCardNumber());
    }

}