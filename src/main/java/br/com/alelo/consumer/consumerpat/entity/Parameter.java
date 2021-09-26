package br.com.alelo.consumer.consumerpat.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "TB_PARAMETER")
public class Parameter extends BaseEntity{


	@Column(name = "NAME")
	private String nameParameter;
	
	@Column(name = "VALUE")
	private String valueParameter;
	
}
