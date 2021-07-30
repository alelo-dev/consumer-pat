package br.com.alelo.consumer.consumerpat.builder;

import br.com.alelo.consumer.consumerpat.dto.UpdateCardDTO;
import br.com.alelo.consumer.consumerpat.dto.UpdateConsumerDTO;
import br.com.alelo.consumer.consumerpat.enumerated.CardType;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

public final class UpdateConsumerBuilder {

    public static UpdateConsumerDTO fullUpdateConsumerDTO() {
        final UpdateConsumerDTO result = new UpdateConsumerDTO();
        result.setId(1L);
        result.setName("name");
        result.setDocumentNumber("documentNumber");
        result.setBirthDate(LocalDate.of(2000, 1, 31));
        result.setMobilePhoneNumber("mobilePhoneNumber");
        result.setResidencePhoneNumber("residencePhoneNumber");
        result.setPhoneNumber("phoneNumber");
        result.setEmail("email");
        result.setStreet("street");
        result.setNumber("number");
        result.setCity("city");
        result.setCountry("country");
        result.setPostalCode("postalCode");
        result.setCards(Arrays.asList(foodAndFuelUpdateCardDTO(), drugstoreUpdateCardDTO(), null));
        return result;
    }

    public static UpdateCardDTO foodAndFuelUpdateCardDTO() {
        UpdateCardDTO result = new UpdateCardDTO();
        result.setId(1L);
        result.setNumber("#1");
        result.setTypes(new HashSet<>(Arrays.asList(CardType.FOOD_CARD, CardType.FUEL_CARD)));
        return result;
    }

    public static UpdateCardDTO drugstoreUpdateCardDTO() {
        UpdateCardDTO result = new UpdateCardDTO();
        result.setNumber("#2");
        result.setTypes(new HashSet<>(Collections.singletonList(CardType.DRUGSTORE_CARD)));
        return result;
    }
}
