package br.com.alelo.consumer.consumerpat.controller.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class AddressDto {

	Long id;
	Long consumerId;
	String street;
	Integer number;
	String city;
	String country;
	String postalCode;

}
