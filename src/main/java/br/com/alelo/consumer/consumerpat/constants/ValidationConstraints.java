package br.com.alelo.consumer.consumerpat.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ValidationConstraints {

    public static final String CONSUMER_NOT_FOUND_BY_ID = "Consumer not found";

    public static final String CONSUMER_NOT_FOUND_BY_FOOD_CARD = "Consumer with Food Card Number {} not found";
    public static final String CONSUMER_NOT_FOUND_BY_FUEL_CARD = "Consumer with Fuel Card Number {} not found";
    public static final String CONSUMER_NOT_FOUND_BY_DRUGSTORE_CARD = "Consumer with Drugstore Card Number {} not found";

    public static final String BALANCE_UPDATE_NOT_ALLOWED = "Update balance is not allowed in this operation";

    public static final String NOT_ENOUGH_BALANCE = "Not enough balance for purchase an item with the value of {}";
}
