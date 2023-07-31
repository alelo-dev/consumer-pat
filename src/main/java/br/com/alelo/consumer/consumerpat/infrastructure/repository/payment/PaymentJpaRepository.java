package br.com.alelo.consumer.consumerpat.infrastructure.repository.payment;

import br.com.alelo.consumer.consumerpat.infrastructure.repository.payment.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentJpaRepository extends JpaRepository<PaymentEntity, UUID> {
}
