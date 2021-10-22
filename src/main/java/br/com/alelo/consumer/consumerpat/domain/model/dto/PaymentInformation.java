package br.com.alelo.consumer.consumerpat.domain.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class PaymentInformation {

    private String establishmentName;
    private int establishmentType;
    private String productDescription;
    private LocalDateTime paymentDate;
    private int cardNumber;
    private BigDecimal value;
}
