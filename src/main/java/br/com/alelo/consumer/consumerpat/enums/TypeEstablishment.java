package br.com.alelo.consumer.consumerpat.enums;

import br.com.alelo.consumer.consumerpat.exception.UnknownEnumValueException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TypeEstablishment {

    FOOD(1),
    DRUGSTORE(2),
    FUEL(3);

    private final int type;

    @JsonValue
    public int getType() {
        return type;
    }

    @JsonCreator
    public static TypeEstablishment of(Integer type) {

        if (null == type) {
            return null;
        }

        for (TypeEstablishment item : TypeEstablishment.values()) {
            if (type.equals(item.getType())) {
                return item;
            }
        }

        throw new UnknownEnumValueException(String.format("TypeEstablishment: unknown value: %s)", type));
    }
}
