package br.com.alelo.consumer.consumerpat.enums;

import java.util.Optional;
import java.util.stream.Stream;

import lombok.Getter;

@Getter
public enum ProductType {

    FOOD(1),
    DRUGSTORE(2),
    FUEL(3),
    ;

    private final int id;

    ProductType(int id) {
        this.id = id;
    }

    public static Optional<ProductType> of(int id) {
        return Stream.of(values())
                .filter(productType -> productType.id == id)
                .findFirst();
    }

}
