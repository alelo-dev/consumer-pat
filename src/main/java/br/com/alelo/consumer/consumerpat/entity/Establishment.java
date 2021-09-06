package br.com.alelo.consumer.consumerpat.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Data;

//TODO NAO FOI DEFINIDO OS CAMPOS OBRIGATORIOS.
@Data
@Entity
public class Establishment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
  @OneToMany
  @JoinColumn(name = "extract_id")
  private List<Extract> extracts;
}
