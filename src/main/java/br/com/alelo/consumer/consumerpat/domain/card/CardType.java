package br.com.alelo.consumer.consumerpat.domain.card;

import br.com.alelo.consumer.consumerpat.infra.handler.exception.badRequest.UndefinedCardTypeBadRequestException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@AllArgsConstructor
public enum CardType {

    FOOD("1111", 1){
        @Override
        public double calculate(final double value) {
            final var cashback  = (value / 100) * 10;
            return value - cashback;
        }
    },
    DRUGSTORE("2222", 2){
        @Override
        public double calculate(final double value) {
            return value;
        }
    },
    FUEL("3333", 3){
        @Override
        public double calculate(final double value) {
            final var tax  = (value / 100) * 35;
            return value + tax;
        }
    };

    @Getter
    private String range;

    @Getter
    private int establishment;

    public static CardType getTypeByRange(final String rangeNumberCard) {
        return Stream.of(values())
                .filter(type -> rangeNumberCard.equals(type.range))
                .findFirst()
                .orElseThrow(() -> new UndefinedCardTypeBadRequestException(String.format("Range do Card [%s] não válido", rangeNumberCard)));
    }
    public static CardType getTypeEstablishment(final int numberEstablishment) {
        return Stream.of(values())
                .filter(type -> numberEstablishment == type.establishment)
                .findFirst()
                .orElseThrow(() -> new UndefinedCardTypeBadRequestException(String.format("Range do Card [%s] não válido", numberEstablishment)));
    }


    public abstract double calculate(final double value);
}

