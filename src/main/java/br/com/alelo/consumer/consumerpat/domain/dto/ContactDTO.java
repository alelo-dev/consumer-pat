package br.com.alelo.consumer.consumerpat.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactDTO {

	private String mobilePhoneNumber;
    private String residencePhoneNumber;
    private String phoneNumber;
    private String email;
	
}
