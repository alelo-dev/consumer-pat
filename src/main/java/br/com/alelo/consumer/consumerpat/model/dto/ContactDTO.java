package br.com.alelo.consumer.consumerpat.model.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContactDTO {
	
	@NotEmpty(message = "Required field")
	private String mobilePhoneNumber;

	@NotEmpty(message = "Required field")
	private String residencePhoneNumber;
	
	@NotEmpty(message = "Required field")
	private String phoneNumber;
	
	@NotEmpty(message = "Required field")
	private String email;
}
