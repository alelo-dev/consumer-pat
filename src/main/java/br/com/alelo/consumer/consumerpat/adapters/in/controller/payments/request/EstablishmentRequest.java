package br.com.alelo.consumer.consumerpat.adapters.in.controller.payments.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EstablishmentRequest {
    private String establishmentName;
    private EstablishmentTypeEnum establishmentType;
}
