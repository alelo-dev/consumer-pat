package br.com.alelo.consumer.consumerpat.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressDTO {
    private Integer id;

    private String street;

    private Integer number;

    private String city;

    private String country;

    private Integer portalCode;
}
