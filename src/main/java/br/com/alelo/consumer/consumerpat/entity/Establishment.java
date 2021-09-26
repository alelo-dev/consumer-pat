package br.com.alelo.consumer.consumerpat.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "TB_ESTABLISHMENT")
public class Establishment extends BaseEntity{
	
	@OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private EstablishmentType establishmentType;

	@Column(name = "NAME")
	private String name;
	
	@Column(name = "DESC")
	private String decription;
	
}
