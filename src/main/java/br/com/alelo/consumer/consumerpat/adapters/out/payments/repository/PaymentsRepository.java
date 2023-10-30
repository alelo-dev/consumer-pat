package br.com.alelo.consumer.consumerpat.adapters.out.payments.repository;

import br.com.alelo.consumer.consumerpat.adapters.out.payments.repository.entity.PaymentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PaymentsRepository extends JpaRepository<PaymentsEntity, UUID> {
}
