package br.com.alelo.consumer.consumerpat.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Getter
@AllArgsConstructor
public enum CardType {

    FOOD(1),
    DRUGSTORE(2),
    FUEL(3);

    private final int type;

    @JsonValue
    public int getType() {
        return type;
    }

    @JsonCreator
    public static CardType of(Integer type) {

        for (CardType item : CardType.values()) {
            if (type.equals(item.getType())) {
                return item;
            }
        }

        throw new ResponseStatusException(BAD_REQUEST, "Valor informado incorreto");
    }
}
