package br.com.alelo.consumerpat.core.dto.v1.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AddressV1ResponseDto {

    private String street;
    private Integer number;
    private String city;
    private String country;
    private String postalCode;
}
