package br.com.alelo.consumer.consumerpat.common.application.port.in;

import br.com.alelo.consumer.consumerpat.common.domain.AccountingEntry;
import br.com.alelo.consumer.consumerpat.common.domain.AccountingEntryType;
import br.com.alelo.consumer.consumerpat.common.domain.Card;
import br.com.alelo.consumer.consumerpat.common.domain.CardBalance;
import br.com.alelo.consumer.consumerpat.common.application.port.out.LoadCardBalancePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Predicate;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CardBalanceQuery implements LoadCardBalancePort {

    private final EntityManager entityManager;

    @Override
    public CardBalance calculate(Card card, LocalDate beginDate, LocalDate endDate) {

        var builder = entityManager.getCriteriaBuilder();
        var query = builder.createQuery(CardBalance.class);
        var root = query.from(AccountingEntry.class);
        var predicates = new ArrayList<>();
        var debitEntry = builder
                .equal(root.get("type").as(AccountingEntryType.class), AccountingEntryType.DEBIT);

        var balance = builder.selectCase()
                .when(debitEntry, builder.neg(root.get("amount")))
                .otherwise(root.get("amount"))
                .as(BigDecimal.class);

        var selection = builder
                .construct(CardBalance.class, root.get("card"),
                        builder.coalesce(builder.sum(balance), BigDecimal.ZERO));

        if (card != null) {
            predicates.add(builder.equal(root.get("card"), card.getCardId()));
        }

        if (beginDate != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("date"), beginDate));
        }

        if (endDate != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("date"), endDate));
        }

        query.select(selection);
        query.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(query).getSingleResult();
    }
}
