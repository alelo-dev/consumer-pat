package br.com.alelo.consumer.consumerpat.sale;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Transaction;
import br.com.alelo.consumer.consumerpat.exception.NotEnoughBalanceException;

public abstract class BalanceSaleProcessor implements SaleProcessor {

    @Override
    public Transaction process(final SaleData saleData) {
        final Card card = saleData.getCard();
        final double calculatedValue = calculateSaleValue(saleData.getSaleValue());

        if (calculatedValue > card.getBalance()) {
            throw new NotEnoughBalanceException(card.getBalance());
        }

        card.setBalance(card.getBalance() - calculatedValue);

        return Transaction.builder()
                .establishment(saleData.getEstablishment())
                .productType(saleData.getProductType())
                .productDescription(saleData.getProductDescription())
                .card(card)
                .value(calculatedValue)
                .build();
    }

    protected abstract double calculateSaleValue(final double saleValue);

}
