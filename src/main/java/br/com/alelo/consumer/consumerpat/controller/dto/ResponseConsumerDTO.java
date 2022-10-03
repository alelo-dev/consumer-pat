package br.com.alelo.consumer.consumerpat.controller.dto;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseConsumerDTO {

	private String name;
	private String documentNumber;
	private String email;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthDate;
	@JsonProperty("phones")
	List<ResponsePhoneDTO> phoneDTOS;
	@JsonProperty("cards")
	private List<ResponseCardDTO> cardDTOS;
	@JsonProperty("address")
	private List<ResponseAddressDTO> addressDTOS;

}
