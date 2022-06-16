package br.com.alelo.consumer.consumerpat.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.alelo.consumer.consumerpat.entity.enums.Type;
import lombok.Data;

@Data
@Entity
public class Establishment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	String name;
	@Enumerated(EnumType.ORDINAL)
	Type type;

	public Establishment() {
		// TODO Auto-generated constructor stub
	}

}
