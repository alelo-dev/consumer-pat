package br.com.alelo.consumer.consumerpat.domain.ledger.entity;

import br.com.alelo.consumer.consumerpat.domain.common.DomainException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum EntryOperationType {

    CREDIT(1),
    DEBIT(2);

    private Integer id;

    public static EntryOperationType getEnum(final Integer entryOperationType) {
        return Arrays.stream(values()).filter(enumItem -> enumItem.getId().equals(entryOperationType))
                .findFirst().orElseThrow(() -> new DomainException("Entry operation type type not found"));
    }
}
