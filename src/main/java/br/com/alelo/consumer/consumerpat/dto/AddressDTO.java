package br.com.alelo.consumer.consumerpat.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
