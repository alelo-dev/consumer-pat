package br.com.alelo.consumer.consumerpat.mock;

import br.com.alelo.consumer.consumerpat.domain.Consumer;
import br.com.alelo.consumer.consumerpat.request.ConsumerRequest;
import br.com.alelo.consumer.consumerpat.response.ConsumerResponse;

import java.time.LocalDate;
import java.util.List;

public class MockConsumerDomain {


    public static Consumer buildConsumer() {
        return Consumer.builder()
                .birthDate(LocalDate.of(1993,12,23))
                .city("Eunapolis")
                .country("Brasil")
                .documentNumber("000.000.000-00")
                .email("beker@gmail.com")
                .mobilePhoneNumber("(73) 99971-3718")
                .numberResidency(20)
                .name("RODRIGO BEKER DA COSTA")
                .postalCode("45821-419")
                .residencePhoneNumber("(73) 3281-1848")
                .street("RUA CEDRO, COLONIAL")
                .cards(List.of())
                .id(1L)
                .build();
    }

    public static Consumer buildConsumerExistCard() {
        return Consumer.builder()
                .birthDate(LocalDate.of(1993,12,23))
                .city("Eunapolis")
                .country("Brasil")
                .documentNumber("000.000.000-00")
                .email("beker@gmail.com")
                .mobilePhoneNumber("(73) 99971-3718")
                .numberResidency(20)
                .name("RODRIGO BEKER DA COSTA")
                .postalCode("45821-419")
                .residencePhoneNumber("(73) 3281-1848")
                .street("RUA CEDRO, COLONIAL")
                .cards(List.of(MockCardDomain.buildCard()))
                .id(2L)
                .build();
    }

    public static ConsumerResponse buildConsumerResponse() {
        return ConsumerResponse.builder()
                .birthDate(LocalDate.of(1993,12,23))
                .city("Eunapolis")
                .country("Brasil")
                .documentNumber("000.000.000-00")
                .email("beker@gmail.com")
                .mobilePhoneNumber("(73) 99971-3718")
                .numberResidency(20)
                .name("RODRIGO BEKER DA COSTA")
                .postalCode("45821-419")
                .residencePhoneNumber("(73) 3281-1848")
                .street("RUA CEDRO, COLONIAL")
                .cards(List.of())
                .id(1L)
                .build();
    }

    public static ConsumerRequest buildConsumerRequest() {
        return ConsumerRequest.builder()
                .birthDate(LocalDate.of(1993,12,23))
                .city("Eunapolis")
                .country("Brasil")
                .documentNumber("000.000.000-00")
                .email("beker@gmail.com")
                .mobilePhoneNumber("(73) 99971-3718")
                .numberResidency(20)
                .name("RODRIGO BEKER DA COSTA")
                .postalCode("45821-419")
                .residencePhoneNumber("(73) 3281-1848")
                .street("RUA CEDRO, COLONIAL")
                .build();
    }
}
