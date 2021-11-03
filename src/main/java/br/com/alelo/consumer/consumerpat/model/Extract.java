package br.com.alelo.consumer.consumerpat.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Classe de domínio representando uma operação comercial (imutável).
 * Nomes das tabelas no BD em 'plural'.
 *
 */
@Entity
@Table(name="extracts")
public final class Extract {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private final String productDescription;
    
    private final LocalDateTime dateBuy;
    
    private final BigDecimal value;

    @ManyToOne
    private final Card card;
    
    @ManyToOne
    private final Establishment establishment;

    public Extract() {
        this.productDescription = null;
        this.dateBuy = null;
        this.card = null;
        this.value = null;
        this.establishment = null;
    }
    
    public Extract(Establishment establishment, String productDescription, LocalDateTime dateBuy, Card card, BigDecimal value) {
        this.establishment = establishment;
        this.productDescription = productDescription;
        this.dateBuy = dateBuy;
        this.card = card;
        this.value = value;
    }

	public long getId() {
		return id;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public LocalDateTime getDateBuy() {
		return dateBuy;
	}

	public BigDecimal getValue() {
		return value;
	}

	public Card getCard() {
		return card;
	}

	public Establishment getEstablishment() {
		return establishment;
	}

	@Override
	public int hashCode() {
		return Objects.hash(card, dateBuy, establishment, id, productDescription, value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		Extract extract = (Extract) obj;
		
		return id == extract.id
				&& Objects.equals(card, extract.card) 
				&& Objects.equals(dateBuy, extract.dateBuy)
				&& Objects.equals(establishment, extract.establishment) 
				&& Objects.equals(productDescription, extract.productDescription) 
				&& Objects.equals(value, extract.value);
	}
}