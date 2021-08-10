package br.com.alelo.consumer.consumerpat.dto;

import br.com.alelo.consumer.consumerpat.entity.Extract;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;


@Data
public class ExtractDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    int establishmentNameId;
    String establishmentName;
    String productDescription;
    Date dateBuy;
    int cardNumber;
    double value;

    public ExtractDTO(){

    }

    public ExtractDTO(int id, int establishmentNameId, String establishmentName, String productDescription, Date dateBuy, int cardNumber, double value) {
        this.id = id;
        this.establishmentNameId = establishmentNameId;
        this.establishmentName = establishmentName;
        this.productDescription = productDescription;
        this.dateBuy = dateBuy;
        this.cardNumber = cardNumber;
        this.value = value;
    }

    public ExtractDTO(String productDescription, Date dateBuy, int cardNumber, double value) {
        this.productDescription = productDescription;
        this.dateBuy = dateBuy;
        this.cardNumber = cardNumber;
        this.value = value;
    }

    public ExtractDTO(String establishmentName, String productDescription, Date dateBuy, int cardNumber, double value) {
        this.establishmentName = establishmentName;
        this.productDescription = productDescription;
        this.dateBuy = dateBuy;
        this.cardNumber = cardNumber;
        this.value = value;
    }


    public Extract mapToEntity() {
        Extract extract = new Extract();
        extract.setId(this.getId());
        extract.setEstablishmentNameId(this.getEstablishmentNameId());
        extract.setEstablishmentName(this.getEstablishmentName());
        extract.setProductDescription(this.getProductDescription());
        extract.setDateBuy(this.getDateBuy());
        extract.setCardNumber(this.getCardNumber());
        extract.setValue(this.getValue());
        return extract;
    }
}
