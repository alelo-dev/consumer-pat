package br.com.alelo.consumer.consumerpat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactDTO {

	private int mobilePhoneNumber;
    private int residencePhoneNumber;
    private int phoneNumber;
    private String email;
	
}
