package br.com.alelo.consumer.consumerpat.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.alelo.consumer.consumerpat.entity.enums.EstablishmentTypeEnum;
import lombok.Data;


@Data
@Entity(name = "extract")
public class Extract {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private Long id; 
	
	@Enumerated(EnumType.STRING)
	@Column(name = "establishmentType")
    private EstablishmentTypeEnum establishmentType;
    
	@Column(name = "establishmentName")
    private String establishmentName;
    
	@Column(name = "productDescription")
    private String productDescription;
    
	@Column(name = "dateBuy")
    private Date dateBuy;
    
	@Column(name = "cardNumber")
    private int cardNumber;
    
	@Column(name = "value")
    private double value;
    
}
