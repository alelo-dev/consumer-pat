package br.com.alelo.consumer.consumerpat.integration.rest.controller.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressRequestV1 {
    private String street;
    private int number;
    private String city;
    private String country;
    private int portalCode;
}
