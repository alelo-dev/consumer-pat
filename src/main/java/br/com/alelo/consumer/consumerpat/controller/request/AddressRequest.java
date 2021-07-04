package br.com.alelo.consumer.consumerpat.controller.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
@AllArgsConstructor
public class AddressRequest {

    @JsonProperty("street")
    private String street;

    @JsonProperty("city")
    private String city;

    @JsonProperty("number")
    private int number;

    @JsonProperty("country")
    private String country;

    @JsonProperty("portal_code")
    private int portalCode;
}
