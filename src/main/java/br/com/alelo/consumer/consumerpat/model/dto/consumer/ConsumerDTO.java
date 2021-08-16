package br.com.alelo.consumer.consumerpat.model.dto.consumer;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import br.com.alelo.consumer.consumerpat.model.dto.card.CardDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConsumerDTO implements Serializable{

	private static final long serialVersionUID = 1L;
		
	private Long id;
	private String name;
	private Integer documentNumber;
	private LocalDate birthDate;

	private ContactDTO contact;
	private AddressDTO address;
	private List<CardDTO> cards;	
}
