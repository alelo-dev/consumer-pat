package br.com.alelo.consumer.consumerpat.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Address {
    private String street;
    private int number;
    private String city;
    private String country;
    @JsonProperty("portal_code")
    private String portalCode;

}
