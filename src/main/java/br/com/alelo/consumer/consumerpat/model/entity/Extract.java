package br.com.alelo.consumer.consumerpat.model.entity;

import java.util.Date;

import javax.persistence.Entity;

import br.com.alelo.consumer.consumerpat.model.entity.base.EntityBase;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
@Entity
public class Extract extends EntityBase {

    private String establishmentName;
    private String productDescription;
    private Date dateBuy;
    private Long cardNumber;
    private Double value;
	public Extract(String establishmentName, String productDescription, Date dateBuy, Long cardNumber, Double value) {
		this.establishmentName = establishmentName;
		this.productDescription = productDescription;
		this.dateBuy = dateBuy;
		this.cardNumber = cardNumber;
		this.value = value;
	}
    
    
}
