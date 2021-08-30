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
public class ContactDTO {

	@ApiModelProperty(value = "Consumer's mobile contact number", example = "+5511981234567")
	private String mobilePhoneNumber;
	@ApiModelProperty(value = "Consumer's phone number", example = "+551125503322")
    private String phoneNumber;
    @ApiModelProperty(value = "Consumer's residential number", example = "+551125513456")
    private String residencePhoneNumber;
    @ApiModelProperty(value = "Consumer's e-mail address", example = "jose.couves@email.com")
    private String email;
	
}
