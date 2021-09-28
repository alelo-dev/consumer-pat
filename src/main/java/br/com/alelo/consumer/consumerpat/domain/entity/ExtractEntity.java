package br.com.alelo.consumer.consumerpat.domain.entity;

import br.com.alelo.consumer.consumerpat.domain.dto.v2.BuyDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExtractEntity {

    public ExtractEntity(BuyDTO buyDTO) {
        this.establishmentNameId = buyDTO.getEstablishmentType();
        this.establishmentName = buyDTO.getEstablishmentName();
        this.productDescription = buyDTO.getProductDescription();
        this.dateBuy = new Date();
        this.cardNumber = buyDTO.getCardNumber();
        this.value = buyDTO.getValue();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    int id;

    int establishmentNameId;

    String establishmentName;

    String productDescription;

    Date dateBuy;

    int cardNumber;

    Double value;

}
