package br.com.alelo.consumer.consumerpat.dto;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EstablishmentResponseDTO implements Serializable{

	private Long id;
	
	private String name;
	
	private String description;
}
