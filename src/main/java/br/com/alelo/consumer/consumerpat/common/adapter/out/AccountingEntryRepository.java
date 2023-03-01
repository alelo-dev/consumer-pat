package br.com.alelo.consumer.consumerpat.common.adapter.out;

import br.com.alelo.consumer.consumerpat.common.domain.AccountingEntry;
import br.com.alelo.consumer.consumerpat.common.application.port.out.SaveAccountingEntryPort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountingEntryRepository extends JpaRepository<AccountingEntry, Long>, SaveAccountingEntryPort {
}
