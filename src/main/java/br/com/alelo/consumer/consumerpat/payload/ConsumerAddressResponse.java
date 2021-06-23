package br.com.alelo.consumer.consumerpat.payload;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ConsumerAddressResponse {
	
	private String street;
	private Integer number;
	private String city;
	private String country;
	private Integer portalCode;

}