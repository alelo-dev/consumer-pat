package br.com.alelo.consumer.consumerpat.service.dto.request;

import br.com.alelo.consumer.consumerpat.entity.enums.EstablishmentTypeEnum;
import lombok.Data;

@Data
public class ExtractRequest {

    private EstablishmentTypeEnum establishmentType;
    
    private String establishmentName;
    
    private String productDescription;
    
    private int cardNumber;
    
    private double value;
    
}
