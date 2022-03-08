package br.com.alelo.consumer.consumerpat.model;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@Entity
@EqualsAndHashCode
@Builder
public class Consumer implements Serializable{

    /**
	 * @author raul.candido
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
    private String name;
    private int documentNumber;
    private Date birthDate;

	@OneToOne(cascade = CascadeType.ALL)
    private Contact contact;

	@OneToOne(cascade = CascadeType.ALL)
	private Adress adress;

    @OneToMany(fetch = FetchType.LAZY)
	private List<Card> cards;

}
