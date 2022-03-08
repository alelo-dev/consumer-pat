package br.com.alelo.consumer.consumerpat.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode
@Builder
public class Contact implements Serializable{

	/**
	 * @author raul.candido
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String mobilePhoneNumber;
	private String residencePhoneNumber;
	private String phoneNumber;
	private String email;
}
