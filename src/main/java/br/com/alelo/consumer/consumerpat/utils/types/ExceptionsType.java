package br.com.alelo.consumer.consumerpat.utils.types;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionsType {

    CARD_ILLEGAL_BALANCE_UPDATE("card.illegal.balance.update", "CARD03E"),
    CARD_INVALID_NUMBER("card.invalid.number", "CARD04E"),
    CARD_MISSING_NUMBER("card.missing.number", "CARD03E"),
    CARD_NOT_FOUND("card.not.found", "CARD01E"),
    CARD_NOT_FROM_CONSUMER("card.not.from.consumer", "CARD02E"),
    CONSUMER_INVALID_DOCUMENT_NUMBER("consumer.invalid.document.number", "CONS03E"),
    CONSUMER_MISSING_FIELD("consumer.missing.field", "CONS02E"),
    CONSUMER_NOT_FOUND("consumer.not.found", "CONS01E"),
    ESTABLISHMENT_MISSING_ID("establishment.missing.id", "EST02E"),
    ESTABLISHMENT_NOT_FOUND("establishment.not.found", "EST01E"),
    PURCHASE_BLOCKED_BALANCE("purchase.blocked.balance", "BUY01E"),
    PURCHASE_BLOCKED_DISCONTINUED("purchase.blocked.discontinued", "BUY01E"),
    PURCHASE_BLOCKED_TYPE("purchase.blocked.type", "BUY03E");
    private final String message;

    private final String code;
}
