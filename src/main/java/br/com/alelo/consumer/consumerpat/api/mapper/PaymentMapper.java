package br.com.alelo.consumer.consumerpat.api.mapper;

import org.springframework.stereotype.Component;

import br.com.alelo.consumer.consumerpat.api.dto.input.PaymentInput;
import br.com.alelo.consumer.consumerpat.domain.model.dto.PaymentInformation;

@Component
public class PaymentMapper {
    
    public PaymentInformation toDomainObject(PaymentInput input) {
        return PaymentInformation.builder()
            .establishmentName(input.getEstablishmentName())
            .establishmentType(input.getEstablishmentType())
            .productDescription(input.getProductDescription())
            .cardNumber(input.getCardNumber())
            .value(input.getValue())
            .build();
    }
}
