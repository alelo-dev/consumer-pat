package br.com.alelo.consumer.consumerpat.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "TB_CARD_TYPE")
public class CardType extends BaseEntity{
	

	@Column(name = "NAME")
	private String name;
	
	@Column(name = "DESC")
	private String decription;

}
