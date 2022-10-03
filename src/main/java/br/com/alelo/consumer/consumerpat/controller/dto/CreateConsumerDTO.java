package br.com.alelo.consumer.consumerpat.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateConsumerDTO {

	private String name;
	private String documentNumber;
	private String email;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthDate;
	@JsonProperty("phones")
	private List<CreatePhoneDTO> createPhoneDTOS;
	@JsonProperty("cards")
	private List<CreateCardDTO> createCardDTOS;
	@JsonProperty("address")
	private List<CreateAddressDTO> createAddressDTOS;
}
