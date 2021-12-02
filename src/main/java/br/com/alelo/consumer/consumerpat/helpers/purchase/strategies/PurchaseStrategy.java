package br.com.alelo.consumer.consumerpat.helpers.purchase.strategies;

import br.com.alelo.consumer.consumerpat.helpers.purchase.enums.PurchaseNamesEnum;

/**
 * Common interface for all purchase strategies
 */
public interface PurchaseStrategy {
    double buy(final int cardNumber, double value);
    PurchaseNamesEnum getPurchaseName();
}
