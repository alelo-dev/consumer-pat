package br.com.alelo.consumer.consumerpat.model.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

import br.com.alelo.consumer.consumerpat.model.enumeration.CardType;

@Builder
@Getter
public class ExtractDTO {

    private CardType establishmentNameId;
    private String establishmentName;
    private String productDescription;
    private LocalDate dateBuy;
    private ConsumerCardDTO consumerCard;
    private Double value;
    
}