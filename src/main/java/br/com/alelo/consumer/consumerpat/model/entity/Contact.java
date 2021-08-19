package br.com.alelo.consumer.consumerpat.model.entity;

import javax.persistence.Entity;

import br.com.alelo.consumer.consumerpat.model.entity.base.EntityBase;
import lombok.Data;

@Data
@Entity
public class Contact extends EntityBase{
	
    private Integer mobilePhoneNumber;
    private Integer residencePhoneNumber;
    private Integer phoneNumber;
    private String email;
    
}
