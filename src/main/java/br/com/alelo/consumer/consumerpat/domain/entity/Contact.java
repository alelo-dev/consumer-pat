package br.com.alelo.consumer.consumerpat.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@Entity
@Table(name = "tb_contact")
public class Contact {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(mappedBy = "contact")
	@JsonIgnore
    private Consumer consumer;
	
	@Column(nullable = false)
    private String email;

	@JsonInclude(value = Include.NON_NULL)
    private String phoneMobile;
	
	@JsonInclude(value = Include.NON_NULL)
    private String phoneResidence;
	
	@JsonInclude(value = Include.NON_NULL)
    private String phoneOther;

}
