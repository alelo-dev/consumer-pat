package br.com.alelo.consumer.consumerpat.model.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdressVO {

	@NotEmpty(message = "Required Field")
	private String street;

	@NotNull(message = "Required Field")
	private Integer number;

	@NotEmpty(message = "Required Field")
	private String city;
	
	@NotEmpty(message = "Required Field")
	private String country;
	
	@NotNull(message = "Required Field")
	private Integer portalCode;
}
