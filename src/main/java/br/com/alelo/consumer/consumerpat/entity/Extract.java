package br.com.alelo.consumer.consumerpat.entity;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import lombok.*;
import org.apache.logging.log4j.util.Strings;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Extract {

    @Id
    private int id;
    private int establishmentNameId;
    private String establishmentName;
    private String productDescription;
    private Date dateBuy;
    private int cardNumber;
    private double value;

    public Extract(String productDescription, Date dateBuy, int cardNumber, double value) {
        new Extract(Strings.EMPTY, productDescription, dateBuy, cardNumber, value);
    }

    public Extract(String establishmentName, String productDescription, Date dateBuy, int cardNumber, double value) {
        this.establishmentName = establishmentName;
        this.productDescription = productDescription;
        this.dateBuy = dateBuy;
        this.cardNumber = cardNumber;
        this.value = value;
    }
}
