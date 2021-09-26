package br.com.alelo.consumer.consumerpat.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "TB_CONTACT")
public class Contact extends BaseEntity{

	@Column(name = "TYPE_PHONE")
	private Integer phoneType;
	
	@Column(name = "DDD_NUMBER")
	private Integer dddNumber;

	@Column(name = "PHONE_NUMBER")
	private Long phoneNumber;

}
