package br.com.alelo.consumer.consumerpat.common.application.port.in;

import br.com.alelo.consumer.consumerpat.common.domain.AccountingEntry;
import br.com.alelo.consumer.consumerpat.common.domain.AccountingEntryType;
import br.com.alelo.consumer.consumerpat.payment.domain.Payment;
import br.com.alelo.consumer.consumerpat.recharge.domain.Recharge;
import org.springframework.stereotype.Component;

@Component
public class AccountingEntryConverter {

    public AccountingEntry deposit(Recharge recharge) {

        var accountingEntry = new AccountingEntry();

        accountingEntry.setCard(recharge.getCard());
        accountingEntry.setAmount(recharge.getAmount());
        accountingEntry.setType(AccountingEntryType.CREDIT);
        accountingEntry.setDate(recharge.getRechargedAt());

        return accountingEntry;
    }

    public AccountingEntry withdraw(Payment payment) {

        var accountingEntry = new AccountingEntry();

        accountingEntry.setCard(payment.getCard());
        accountingEntry.setAmount(payment.getAmount());
        accountingEntry.setType(AccountingEntryType.DEBIT);
        accountingEntry.setDate(payment.getDate());

        return accountingEntry;
    }
}
