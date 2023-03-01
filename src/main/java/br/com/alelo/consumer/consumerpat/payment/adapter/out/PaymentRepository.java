package br.com.alelo.consumer.consumerpat.payment.adapter.out;

import br.com.alelo.consumer.consumerpat.payment.domain.Payment;
import br.com.alelo.consumer.consumerpat.payment.application.port.out.SavePaymentPort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long>, SavePaymentPort {
}
