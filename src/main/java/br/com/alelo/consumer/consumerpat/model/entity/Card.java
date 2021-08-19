package br.com.alelo.consumer.consumerpat.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import br.com.alelo.consumer.consumerpat.model.entity.base.EntityBase;
import br.com.alelo.consumer.consumerpat.model.enumeration.TypeCard;
import lombok.Data;

@Data
@Entity
public class Card extends EntityBase {
	
	private TypeCard typecard;
	
	private Double balance;
	
    @OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_card")
    private List<Extract> extracts = new ArrayList<>();

}
