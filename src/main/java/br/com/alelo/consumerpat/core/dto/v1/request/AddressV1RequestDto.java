package br.com.alelo.consumerpat.core.dto.v1.request;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AddressV1RequestDto {

    private String street;
    private Integer number;
    private String city;
    private String country;
    private String postalCode;
}
