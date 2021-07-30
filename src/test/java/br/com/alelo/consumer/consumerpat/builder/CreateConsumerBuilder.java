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
        result.setCards(Arrays.asList(foodAndFuelCreateCardDTO(), drugstoreCreateCardDTO(), null));
        return result;
    }

    public static CreateCardDTO foodAndFuelCreateCardDTO() {
        CreateCardDTO result = new CreateCardDTO();
        result.setBalance(BigDecimal.TEN);
        result.setNumber("#1");
        result.setTypes(new HashSet<>(Arrays.asList(CardType.FOOD_CARD, CardType.FUEL_CARD)));
        return result;
    }

    public static CreateCardDTO drugstoreCreateCardDTO() {
        CreateCardDTO result = new CreateCardDTO();
        result.setNumber("#2");
        result.setTypes(new HashSet<>(Collections.singletonList(CardType.DRUGSTORE_CARD)));
        return result;
    }
}
