package br.com.alelo.consumer.consumerpat.enums;

import java.util.Arrays;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ContactType {

    MOBILE(1),
    RESIDENCE(2),
    PHONE(3),
    EMAIL(4);

    private final Integer id;

    public static ContactType fromId(Integer id) {
        return Arrays.stream(ContactType.values())
                .filter(e -> e.id.equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Unsupported id %s.", id)));
    }

}
