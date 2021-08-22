package br.com.alelo.consumerpat.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddressDomain {

    private String street;
    private Integer number;
    private String city;
    private String country;
    private String portalCode;
}
