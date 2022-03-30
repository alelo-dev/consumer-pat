package br.com.alelo.consumer.consumerpat.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class AddressDTO {
    private Integer id;

    @NonNull
    private String street;

    private Integer number;

    @NonNull
    private String city;

    @NonNull
    private String country;

    @NonNull
    private Integer portalCode;
}
