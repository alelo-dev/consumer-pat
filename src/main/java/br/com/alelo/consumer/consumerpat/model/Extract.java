package br.com.alelo.consumer.consumerpat.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;


@Data
@Entity
public class Extract {

    @Id
    int id;
    
    int establishmentNameId;
    
    String establishmentName;
    
    String productDescription;
    
    Date dateBuy;
    
    int cardNumber;
    
    double value;

}
