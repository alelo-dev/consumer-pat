package br.com.alelo.consumer.consumerpat.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

//TODO NAO FOI DEFINIDO OS CAMPOS OBRIGATORIOS.
@Data
@Entity
public class Contracts {

	@Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
	private String mobilePhoneNumber;
	private String residencePhoneNumber;
	private String phoneNumber;
	private String email;
	@OneToOne
  @JoinColumn(name = "consumer_id")
	private Consumer consumer;
}
