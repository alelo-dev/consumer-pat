package br.com.alelo.consumer.consumerpat.controller.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAddressDTO {

	private String street;
	private int number;
	private String city;
	private String country;
	private int portalCode;
}
