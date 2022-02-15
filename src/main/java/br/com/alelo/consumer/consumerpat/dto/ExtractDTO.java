package br.com.alelo.consumer.consumerpat.dto;

import java.util.Date;

import br.com.alelo.consumer.consumerpat.entity.Extract;
import lombok.Data;

@Data
public class ExtractDTO {
	
	int id;
    int establishmentNameId;
    String establishmentName;
    String productDescription;
    Date dateBuy;
    String cardNumber;
    double value;
    
    String establishmentType;
    
    public ExtractDTO(
    		int id, int establishmentNameId, String establishmentName, String productDescription, 
    		Date dateBuy, String cardNumber, double value, String establishmentType) {
    	
    	this.id = id;
        this.establishmentNameId = establishmentNameId;
        this.establishmentName = establishmentName;
        this.productDescription = productDescription;
        this.dateBuy = dateBuy;
        this.cardNumber = cardNumber;
        this.value = value;
        this.establishmentType = establishmentType;
    }    

    
    public Extract tranformDTOToEntity() {
    	return new Extract(id, establishmentNameId, establishmentName, productDescription, dateBuy, cardNumber, value);
    }
    
}
