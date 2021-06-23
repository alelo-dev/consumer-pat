package br.com.alelo.consumer.consumerpat.payload.converter;

import br.com.alelo.consumer.consumerpat.entity.ConsumerAddress;
import br.com.alelo.consumer.consumerpat.payload.ConsumerAddressRequest;
import br.com.alelo.consumer.consumerpat.payload.ConsumerAddressResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConsumerAddressConverter {

	public static ConsumerAddress toEntity(final ConsumerAddressRequest request) {
		return ConsumerAddress.builder()
			.street(request.getStreet())
			.number(request.getNumber())
			.city(request.getCity())
			.country(request.getCountry())
			.portalCode(request.getPortalCode())
			.build();
	}

	public static ConsumerAddressResponse toResponse(final ConsumerAddress response) {
		return ConsumerAddressResponse.builder()
			.street(response.getStreet())
			.number(response.getNumber())
			.city(response.getCity())
			.country(response.getCountry())
			.portalCode(response.getPortalCode())
			.build();
	}

}