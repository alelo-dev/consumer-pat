package br.com.alelo.consumer.consumerpat.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.alelo.consumer.consumerpat.enums.TYPE_CARD;
import lombok.Data;


//TODO Tipando o objeto para nao ter parametros repetitivos
//TODO NAO FOI DEFINIDO OS CAMPOS OBRIGATORIOS.
@Data
@Entity
public class Cards {

	@Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
	@Enumerated(EnumType.ORDINAL)
	private TYPE_CARD type;
	private Integer cardNumber;
	private Double cardBalance;
	@ManyToOne
  @JoinColumn(name = "consumer_id")
	private Consumer consumer;
}
