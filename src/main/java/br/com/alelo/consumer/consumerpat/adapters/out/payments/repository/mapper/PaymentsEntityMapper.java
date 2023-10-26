package br.com.alelo.consumer.consumerpat.adapters.out.payments.repository.mapper;

import br.com.alelo.consumer.consumerpat.adapters.out.payments.repository.entity.EstablishmentEntity;
import br.com.alelo.consumer.consumerpat.adapters.out.payments.repository.entity.PaymentsEntity;
import br.com.alelo.consumer.consumerpat.application.core.domain.payments.Payments;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class PaymentsEntityMapper {
    public PaymentsEntity toPaymentsEntity(Payments payments) {
        var paymentsEntity = new PaymentsEntity();
        var establishment = new EstablishmentEntity();

        BeanUtils.copyProperties(payments, paymentsEntity);
        BeanUtils.copyProperties(payments.getEstablishment(), establishment);

        paymentsEntity.setEstablishment(establishment);

        return paymentsEntity;
    }
}
