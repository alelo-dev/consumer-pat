package utils.types;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionsType {

    CARD_ILLEGAL_BALANCE_UPDATE("card.illegal.balance.update", "CARD03E"),
    CARD_NOT_FOUND("card.not.found", "CARD01E"),
    CARD_NOT_FROM_CONSUMER("card.not.from.consumer", "CARD02E"),
    CONSUMER_MISSING_DOCUMENT("consumer.missing.document", "CONS02E"),
    CONSUMER_NOT_FOUND("consumer.not.found", "CONS01E"),
    ESTABLISHMENT_MISSING_ID("establishment.missing.id", "EST02E"),
    ESTABLISHMENT_NOT_FOUND("establishment.not.found", "EST01E"),
    PURCHASE_BLOCKED_BALANCE("purchase.blocked.balance", "BUY01E"),
    PURCHASE_BLOCKED_DISCONTINUED("purchase.blocked.discontinued", "BUY01E"),
    PURCHASE_BLOCKED_TYPE("purchase.blocked.type", "BUY03E");
    private final String message;

    private final String code;
}
