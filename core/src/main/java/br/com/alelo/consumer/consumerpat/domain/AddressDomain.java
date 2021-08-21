package br.com.alelo.consumer.consumerpat.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddressDomain {

    private Long id;
    private String street;
    private Integer number;
    private String city;
    private String country;
    private String portalCode;
}
