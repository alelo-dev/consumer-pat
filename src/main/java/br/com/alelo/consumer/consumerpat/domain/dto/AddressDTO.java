package br.com.alelo.consumer.consumerpat.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {
	
	@ApiModelProperty(value = "Street name", example = "A Street")
	private String street;
	@ApiModelProperty(value = "Address' number", example = "1056")
    private String number;
    @ApiModelProperty(value = "City", example = "A City")
	private String city;
    @ApiModelProperty(value = "Country", example = "Brazil")
    private String country;
    @ApiModelProperty(value = "Address' postla code", example = "0010578")
    private String postalCode;

}
