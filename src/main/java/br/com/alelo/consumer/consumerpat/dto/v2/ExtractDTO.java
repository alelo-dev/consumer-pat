package br.com.alelo.consumer.consumerpat.dto.v2;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.alelo.consumer.consumerpat.entity.ExtractEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExtractDTO {
    
    //Usaria MApstruct
    public ExtractDTO (ExtractEntity entity){
        this.id = entity.getId();
        this.cardNumber = entity.getCardNumber();
        this.dateBuy = entity.getDateBuy();
        this.establishmentName = entity.getEstablishmentName();
        this.establishmentNameId = entity.getEstablishmentNameId();
        this.productDescription = entity.getProductDescription();
        this.value = entity.getValue();
    }

    private Integer id;
    private Integer establishmentNameId;
    private String establishmentName;
    private String productDescription;
    private LocalDate dateBuy;
    private Integer cardNumber;
    private BigDecimal value;
}
