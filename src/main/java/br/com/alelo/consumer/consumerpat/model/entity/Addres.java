package br.com.alelo.consumer.consumerpat.model.entity;

import javax.persistence.Entity;

import br.com.alelo.consumer.consumerpat.model.entity.base.EntityBase;
import lombok.Data;

@Data
@Entity
public class Addres extends EntityBase {
	
    private String street;
    private Integer number;
    private String city;
    private String country;
    private Integer portalCode;

}
