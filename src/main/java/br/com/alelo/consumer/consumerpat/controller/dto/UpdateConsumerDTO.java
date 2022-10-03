package br.com.alelo.consumer.consumerpat.controller.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.alelo.consumer.consumerpat.entity.Card;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateConsumerDTO {

	private String name;
	private String documentNumber;
	private String email;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthDate;
	@JsonProperty("phone")
	private List<UpdatePhoneDTO> updatePhoneDTOS;
	@JsonProperty("address")
	private List<UpdateAddressDTO> updateAddressDTOS;

}
