package br.com.alelo.consumer.consumerpat.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class ConsumerContactResponseDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4083151088301653229L;

	private Long id;

	private Integer phoneType;
	
	private Integer dddNumber;

	private Long phoneNumber;

}
