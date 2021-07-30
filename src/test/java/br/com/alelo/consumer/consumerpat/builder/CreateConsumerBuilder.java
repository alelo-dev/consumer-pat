package br.com.alelo.consumer.consumerpat.builder;

import br.com.alelo.consumer.consumerpat.dto.CreateCardDTO;
import br.com.alelo.consumer.consumerpat.dto.CreateConsumerDTO;
import br.com.alelo.consumer.consumerpat.enumerated.CardType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

public final class CreateConsumerBuilder {

    public static CreateConsumerDTO fullCreateConsumerDTO() {
        final CreateConsumerDTO result = new CreateConsumerDTO();
        result.setName("create-name");
        result.setDocumentNumber("create-documentNumber");
        result.setBirthDate(LocalDate.of(2000, 1, 31));
        result.setMobilePhoneNumber("create-mobilePhoneNumber");
        result.setResidencePhoneNumber("create-residencePhoneNumber");
        result.setPhoneNumber("create-phoneNumber");
        result.setEmail("create-email");
        result.setStreet("create-street");
        result.setNumber("create-number");
        result.setCity("create-city");
        result.setCountry("create-country");
        result.setPostalCode("create-postalCode");
        result.setCards(Arrays.asList(foodAndFuelCreateCardDTO(), drugstoreCreateCardDTO(), null));
        return result;
    }

    public static CreateCardDTO foodAndFuelCreateCardDTO() {
        CreateCardDTO result = new CreateCardDTO();
        result.setBalance(BigDecimal.TEN);
        result.setNumber("create-#1");
        result.setTypes(new HashSet<>(Arrays.asList(CardType.FOOD_CARD, CardType.FUEL_CARD)));
        return result;
    }

    public static CreateCardDTO drugstoreCreateCardDTO() {
        CreateCardDTO result = new CreateCardDTO();
        result.setNumber("create-#2");
        result.setTypes(new HashSet<>(Collections.singletonList(CardType.DRUGSTORE_CARD)));
        return result;
    }
}
