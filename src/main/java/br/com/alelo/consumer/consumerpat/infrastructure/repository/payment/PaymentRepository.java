package br.com.alelo.consumer.consumerpat.infrastructure.repository.payment;

import br.com.alelo.consumer.consumerpat.domain.payment.entity.Payment;
import br.com.alelo.consumer.consumerpat.domain.payment.repository.DomainPaymentRepository;
import br.com.alelo.consumer.consumerpat.infrastructure.repository.payment.converter.PaymentConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
@RequiredArgsConstructor
public class PaymentRepository implements DomainPaymentRepository {

    private final PaymentJpaRepository paymentRepository;
    private final PaymentConverter paymentConverter;

    @Override
    public void save(Payment payment) {
        var paymentEntity = paymentConverter.toEntity(payment);
        paymentEntity.setCreatedAt(LocalDateTime.now());
        paymentRepository.save(paymentEntity);
    }
}
