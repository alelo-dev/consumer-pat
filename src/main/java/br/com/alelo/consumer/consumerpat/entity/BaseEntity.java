package br.com.alelo.consumer.consumerpat.entity;

/**
 * Classe de reuso das entidades contendo os metodos e campos que possam ser reutilizados e sobreecritos através do get
 * portanto a anotação sair do atributo e vai para o método.
 * @author julio.silva
 * @date 23/10/2023
 */
public class BaseEntity {
	
	 protected static final int INITIAL_VALUE_ZERO = 0;

	public Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
