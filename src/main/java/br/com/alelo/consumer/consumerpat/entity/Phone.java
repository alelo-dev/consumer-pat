package br.com.alelo.consumer.consumerpat.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.*;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Phone {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Integer id;
	@Column(name = "DDD")
	private String ddd;
	@Column(name = "NUMBER")
	private String number;
	@Column(name = "PHONE_TYPE")
	private Integer phoneType;

}
