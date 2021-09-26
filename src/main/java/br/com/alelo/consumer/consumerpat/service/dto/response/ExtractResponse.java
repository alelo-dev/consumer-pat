package br.com.alelo.consumer.consumerpat.service.dto.response;

import java.util.Date;

import br.com.alelo.consumer.consumerpat.entity.enums.EstablishmentTypeEnum;
import lombok.Data;

@Data
public class ExtractResponse {

	private Long id;
	
	private EstablishmentTypeEnum establishmentType;
    
    private String establishmentName;
    
    private String productDescription;
    
    private Date dateBuy;
    
    private int cardNumber;
    
    private double value;
}
