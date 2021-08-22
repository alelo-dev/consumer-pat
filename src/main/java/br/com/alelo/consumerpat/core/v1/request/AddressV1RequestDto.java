package br.com.alelo.consumerpat.core.v1.request;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Getter
public class AddressV1RequestDto {

    private String street;
    private Integer number;
    private String city;
    private String country;
    private String postalCode;
}
