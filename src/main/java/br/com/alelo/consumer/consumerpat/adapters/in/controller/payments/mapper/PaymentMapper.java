package br.com.alelo.consumer.consumerpat.adapters.in.controller.payments.mapper;

import br.com.alelo.consumer.consumerpat.adapters.in.controller.payments.request.PaymentRequest;
import br.com.alelo.consumer.consumerpat.application.core.domain.payments.Establishment;
import br.com.alelo.consumer.consumerpat.application.core.domain.payments.Payments;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class PaymentMapper {
    public Payments toPayment(PaymentRequest paymentRequest) {
        var payments = new Payments();
        var establishment = new Establishment();

        BeanUtils.copyProperties(paymentRequest, payments);
        if(Objects.nonNull(paymentRequest.getEstablishment()))
            BeanUtils.copyProperties(paymentRequest.getEstablishment(), establishment);

        payments.setEstablishment(establishment);
        payments.getEstablishment().setEstablishmentType(paymentRequest.getEstablishment().getEstablishmentType().value());

        return payments;
    }
}
