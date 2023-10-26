package br.com.alelo.consumer.consumerpat.adapters.out.payments.repository;

import br.com.alelo.consumer.consumerpat.adapters.out.payments.repository.entity.PaymentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentsRepository extends JpaRepository<PaymentsEntity, UUID> {
}
