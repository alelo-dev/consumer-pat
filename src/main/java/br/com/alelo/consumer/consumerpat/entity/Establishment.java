package br.com.alelo.consumer.consumerpat.entity;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "Establishment")
public class Establishment  implements Serializable {

	
	private static final long serialVersionUID = 253477474554349883L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;	

	@Column(name = "establishmentType", nullable = false)
	private Integer establishmentType;
	
    
	public Establishment(){
	}


	//-----------Getters and Setters-------------//
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public Integer getEstablishmentType() {
		return establishmentType;
	}


	public void setEstablishmentType(Integer establishmentType) {
		this.establishmentType = establishmentType;
	}


}