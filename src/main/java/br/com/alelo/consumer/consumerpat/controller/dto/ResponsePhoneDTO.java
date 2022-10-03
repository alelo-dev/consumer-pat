package br.com.alelo.consumer.consumerpat.controller.dto;

import br.com.alelo.consumer.consumerpat.enums.PhoneType;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponsePhoneDTO {

	private String ddd;
	private String number;
	private PhoneType phoneType;

}
