package br.com.alelo.consumer.consumerpat.common.application.port.in;

import br.com.alelo.consumer.consumerpat.common.application.port.out.SaveAccountingEntryPort;
import br.com.alelo.consumer.consumerpat.payment.domain.Payment;
import br.com.alelo.consumer.consumerpat.recharge.domain.Recharge;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterActivityUseCase {

    private final AccountingEntryConverter accountingEntryConverter;
    private final SaveAccountingEntryPort saveAccountingEntryPort;

    public void deposit(Recharge recharge) {
        var accountingEntry = accountingEntryConverter.deposit(recharge);
        saveAccountingEntryPort.save(accountingEntry);
    }

    public void withdraw(Payment payment) {
        var accountingEntry = accountingEntryConverter.withdraw(payment);
        saveAccountingEntryPort.save(accountingEntry);
    }
}
