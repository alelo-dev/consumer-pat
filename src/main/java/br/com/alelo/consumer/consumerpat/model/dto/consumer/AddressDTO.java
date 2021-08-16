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
public class AddressDTO implements Serializable{
		
	private static final long serialVersionUID = 1L;
	
	private String street;
	private Integer number;
	private String city;
	private String country;
	private Integer portalCode;
}
