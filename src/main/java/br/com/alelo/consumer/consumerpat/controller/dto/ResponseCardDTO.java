package br.com.alelo.consumer.consumerpat.controller.dto;

import java.util.Date;

import br.com.alelo.consumer.consumerpat.enums.CardType;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCardDTO {

	 private String number;
	 private Double balance;
	 private CardType cardType;
	 private Date expirationdate;
}
