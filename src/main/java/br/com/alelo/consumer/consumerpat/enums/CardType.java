package br.com.alelo.consumer.consumerpat.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Representation of available card types
 *
 * @author mcrj
 */
@Getter
@AllArgsConstructor
public enum CardType {

    FOOD(1), DRUGSTORE(2), FUEL(3);

    private final int code;
}
