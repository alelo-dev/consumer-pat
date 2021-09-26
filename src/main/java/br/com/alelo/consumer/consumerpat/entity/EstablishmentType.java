package br.com.alelo.consumer.consumerpat.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Entity
@Table(name = "TB_ESTABLISHMENT_TYPE")
public class EstablishmentType extends BaseEntity{
	

	@Column(name = "NAME")
	private String name;
	
	@Column(name = "DESC")
	private String decription;

}
