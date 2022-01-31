package br.com.alelo.consumer.consumerpat;

import br.com.alelo.consumer.consumerpat.domain.dto.CardDTO;
import br.com.alelo.consumer.consumerpat.domain.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.domain.entity.Consumer;
import br.com.alelo.consumer.consumerpat.domain.enumeration.CardType;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

public class DataTesteUtils {

    private static Integer CARD_NUMBER = 87654;
    private static Integer ID_ENTITY;


    public static CardDTO getCardDto() {
        CardDTO dto = CardDTO.builder()
                .cardType(CardType.FOOD)
                .cardNumber(CARD_NUMBER)
                .balanceValue(BigDecimal.valueOf(3000.00))
                .consumer(getConsumer())
                .build();

        return dto;
    }


    public static Consumer getConsumer() {
        LocalDate birthDate =  LocalDate.of(1982,12,17);
        Consumer consumer = Consumer.builder()
                .id(ID_ENTITY)
                .birthDate(Date.from(birthDate.atStartOfDay()
                        .atZone(ZoneId.systemDefault())
                        .toInstant()))
                .city("João Pessoa")
                .country("Brazil")
                .documentNumber(123456)
                .name("Sakura Card Captors")
                .street("Rua da mansão Bly")
                .number(41)
                .email("sakura@alelo.com.br")
                .portalCode(45850000)
                .phoneNumber("88888888")
                .residencePhoneNumber("99999999")
                .mobilePhoneNumber("77777777")
                .build();

        return consumer;
    }

    public static ConsumerDTO getConsumerDTO() {
        LocalDate birthDate =  LocalDate.of(1982,12,17);
        ConsumerDTO dto = ConsumerDTO.builder()
                .birthDate(Date.from(birthDate.atStartOfDay()
                        .atZone(ZoneId.systemDefault())
                        .toInstant()))
                .city("João Pessoa")
                .country("Brazil")
                .documentNumber(123456)
                .name("Sakura Card Captors")
                .street("Rua da mansão Bly")
                .number(41)
                .email("sakura@alelo.com.br")
                .portalCode(45850000)
                .phoneNumber("88888888")
                .residencePhoneNumber("99999999")
                .mobilePhoneNumber("77777777")
                .build();

        return dto;
    }


}
