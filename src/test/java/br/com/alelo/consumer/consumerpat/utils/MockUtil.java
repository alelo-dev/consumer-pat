package br.com.alelo.consumer.consumerpat.utils;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.model.dto.DtoBuy;
import br.com.alelo.consumer.consumerpat.model.dto.EstablishmentTypeEnum;
import br.com.alelo.consumer.consumerpat.utils.exception.ProcessException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Objects;


public class MockUtil {
    public static Card getCard(EstablishmentTypeEnum type, double balance) {
        return Card.builder().id(1).cardNumber(123456789123459L).balance(balance).cardType(type.getType()).consumerId(1).build();
    }

    public static Consumer getConsumer() {
        return Consumer.builder()
        .id(10)
        .name("teste")
        .documentNumber(1)
        .birthDate(LocalDate.now().minusYears(30))
        .mobilePhoneNumber(1681973490)
        .residencePhoneNumber(1632428225)
        .email("string")
        .street("Rua d")
        .number(120)
        .city("São Paulo")
        .foodCardBalance(0)
        .foodCardNumber(1234567890123459l)
        .country("Brasil")
        .portalCode(15910001).build();
    }

    public static DtoBuy getDtoBuy() throws ProcessException {
        return new DtoBuy(EstablishmentTypeEnum.DRUG_STONE.getType(), EstablishmentTypeEnum.DRUG_STONE.getName(), 1234567890123456l,  "teste", 10.00 );
    }

    public static String getPathString(String path) throws IOException {
        if (Objects.nonNull(path)) {
            ClassPathResource json = new ClassPathResource(path);
            try (InputStream in = json.getInputStream()){
                return StreamUtils.copyToString(in, StandardCharsets.UTF_8);
            }
        }
        return null;
    }
}