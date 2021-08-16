package br.com.alelo.consumer.consumerpat.model.dto.consumer;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactDTO implements Serializable{
		
	private static final long serialVersionUID = 1L;
	
	private Integer mobilePhoneNumber;
	private Integer residencePhoneNumber;
	private Integer phoneNumber;
	private String email;
}
