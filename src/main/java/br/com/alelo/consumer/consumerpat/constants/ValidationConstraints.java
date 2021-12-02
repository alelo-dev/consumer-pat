package br.com.alelo.consumer.consumerpat.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ValidationConstraints {

    public static final String CONSUMER_NOT_FOUND_BY_FOOD_CARD = "Consumer with Food Card Number {} not found";
    public static final String CONSUMER_NOT_FOUND_BY_FUEL_CARD = "Consumer with Fuel Card Number {} not found";
    public static final String CONSUMER_NOT_FOUND_BY_DRUGSTORE_CARD = "Consumer with Drugstore Card Number {} not found";
}
