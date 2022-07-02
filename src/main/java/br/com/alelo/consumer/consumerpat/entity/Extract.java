package br.com.alelo.consumer.consumerpat.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;


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


    public Extract(int id, int establishmentNameId, String establishmentName, String productDescription, Date dateBuy, int cardNumber, double value) {
        this.id = id;
        this.establishmentNameId = establishmentNameId;
        this.establishmentName = establishmentName;
        this.productDescription = productDescription;
        this.dateBuy = dateBuy;
        this.cardNumber = cardNumber;
        this.value = value;
    }

    public Extract( String productDescription, Date dateBuy, int cardNumber, double value) {
        this.productDescription = productDescription;
        this.dateBuy = dateBuy;
        this.cardNumber = cardNumber;
        this.value = value;
    }

    public Extract(String establishmentName, String productDescription, Date dateBuy, int cardNumber, double value) {
        this.establishmentNameId = establishmentNameId;
        this.establishmentName = establishmentName;
        this.productDescription = productDescription;
        this.dateBuy = dateBuy;
        this.cardNumber = cardNumber;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Extract)) {
            return false;
        }

        Extract extract = (Extract) o;
        
        return id == extract.id 
            && establishmentNameId == extract.establishmentNameId 
            && Objects.equals(establishmentName, extract.establishmentName) 
            && Objects.equals(productDescription, extract.productDescription) 
            && Objects.equals(dateBuy, extract.dateBuy) 
            && cardNumber == extract.cardNumber 
            && value == extract.value;
    }
    
    @Override
    public String toString() {
        return "{" +
            "establishmentName='" + getEstablishmentName() + "'" +
            ", productDescription='" + getProductDescription() + "'" +
            ", dateBuy='" + getDateBuy() + "'" +
            ", cardNumber='" + getCardNumber() + "'" +
            ", value='" + getValue() + "'" +
            "}";
    }

}
