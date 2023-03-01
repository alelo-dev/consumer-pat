package br.com.alelo.consumer.consumerpat.common.application.port.out;

import br.com.alelo.consumer.consumerpat.common.domain.AccountingEntry;

public interface SaveAccountingEntryPort {

    AccountingEntry save(AccountingEntry accountingEntry);
}
