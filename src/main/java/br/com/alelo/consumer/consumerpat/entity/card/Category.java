package br.com.alelo.consumer.consumerpat.entity.card;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;


@Data
@Entity(name = "CATEGORY")
public class Category {

	@Id   
	private Integer id;	
	private String description;	
}
