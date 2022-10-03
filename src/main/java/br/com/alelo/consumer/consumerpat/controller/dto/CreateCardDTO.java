package br.com.alelo.consumer.consumerpat.controller.dto;

import java.util.Date;

import br.com.alelo.consumer.consumerpat.enums.CardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCardDTO {
	
	 private String number;
	 private Double balance;
	 private CardType cardType;
	 private Date expirationdate;

}
