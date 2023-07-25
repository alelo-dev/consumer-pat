package br.com.alelo.consumer.consumerpat.domain.payment.entity;

import br.com.alelo.consumer.consumerpat.domain.common.DomainException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;

@Getter
@ToString
@AllArgsConstructor
public enum EstablishmentType {
    FOOD(1),
    DRUGSTORE(2),
    FUEL(3);

    private Integer id;

    public static EstablishmentType getEnum(final Integer establishmentType) {
        return Arrays.stream(values()).filter(enumItem -> enumItem.getId().equals(establishmentType))
                .findFirst().orElseThrow(() -> new DomainException("Establishment type not found"));
    }
}
