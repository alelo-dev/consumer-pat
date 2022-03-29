package br.com.alelo.consumer.consumerpat.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressDTO {
    Integer id;

    String street;

    Integer number;

    String city;

    String country;

    Integer portalCode;
}
