package br.com.alelo.consumer.consumerpat.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
@Entity(name = "EXTRACT")
public class Extract {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    Integer establishmentNameId;
        
    String establishmentName;
        
    String productDescription;
        
    Date dateBuy;
        
    String cardNumber;
      
    Double value;  
}
