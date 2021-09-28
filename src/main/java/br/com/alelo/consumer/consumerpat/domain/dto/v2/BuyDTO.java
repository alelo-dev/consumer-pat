package br.com.alelo.consumer.consumerpat.domain.dto.v2;

import br.com.alelo.consumer.consumerpat.domain.entity.ExtractEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BuyDTO {

    public static BuyDTO convertEntityToDto(ExtractEntity extractEntity){
        if(extractEntity != null){
            return new BuyDTO(
            extractEntity.getCardNumber(),
            extractEntity.getEstablishmentName(),
            extractEntity.getEstablishmentNameId(),
            extractEntity.getProductDescription(),
            extractEntity.getValue());
        }
        return null;
    }

    private int cardNumber;
    private String establishmentName;
    private int establishmentType;
    private String productDescription;
    private double value;
}
