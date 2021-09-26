package br.com.alelo.consumer.consumerpat.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class ConsumerResponseDTO implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3086044348994396837L;

	private Long id;

	private String name;

	private Long documentNumber;

	private String birthDate;
	
	private String email;
	
	private ConsumerContactResponseDTO consumerContact;
	
	private AddressResponseDTO address;
	
	private List<CardConsumerResponseDTO> cards;
	
}
