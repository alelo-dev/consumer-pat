package br.com.alelo.consumer.consumerpat.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {

    @JsonProperty(value = "street")
    @ApiModelProperty(value = "Address street name")
    private String street;

    @JsonProperty(value = "number")
    @ApiModelProperty(value = "Address number")
    private int number;

    @JsonProperty(value = "city")
    @ApiModelProperty(value = "Address city name")
    private String city;

    @JsonProperty(value = "country")
    @ApiModelProperty(value = "Address contry name")
    private String country;

    @JsonProperty(value = "postalCode")
    @ApiModelProperty(value = "Address postal code")
    private int postalCode;
}
