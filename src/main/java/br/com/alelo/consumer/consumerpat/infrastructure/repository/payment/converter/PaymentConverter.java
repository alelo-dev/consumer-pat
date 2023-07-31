package br.com.alelo.consumer.consumerpat.infrastructure.repository.payment.converter;

import br.com.alelo.consumer.consumerpat.domain.payment.entity.Payment;
import br.com.alelo.consumer.consumerpat.infrastructure.repository.payment.entity.EstablishmentEntity;
import br.com.alelo.consumer.consumerpat.infrastructure.repository.payment.entity.PaymentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentConverter {

    public PaymentEntity toEntity(final Payment payment) {
        return PaymentEntity.builder()
                .id(payment.getId())
                .establishment(EstablishmentEntity.builder()
                        .establishmentName(payment.getEstablishment().getEstablishmentName())
                        .establishmentType(payment.getEstablishment().getEstablishmentType())
                        .build())
                .productDescription(payment.getProductDescription())
                .buyDate(payment.getBuyDate())
                .cardNumber(payment.getCardNumber().getCardNumber())
                .amount(payment.getAmount())
                .build();
    }
}
