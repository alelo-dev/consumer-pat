package br.com.alelo.consumer.consumerpat.adapters.in.controller.payments.request;

import lombok.Data;

@Data
public class EstablishmentRequest {
    private String establishmentName;
    private EstablishmentTypeEnum establishmentType;
}
