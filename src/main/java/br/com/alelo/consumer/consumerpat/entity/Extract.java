package br.com.alelo.consumer.consumerpat.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
@Builder
@AllArgsConstructor
public class Extract {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    int establishmentNameId;
    String establishmentName;
    String productDescription;
    Date dateBuy;
    int cardNumber;
    double value;

    public Extract(String productDescription, Date dateBuy, int cardNumber, double value) {
        this(0, 0, null, productDescription, dateBuy, cardNumber, value);
    }

    public Extract(String establishmentName, String productDescription, Date dateBuy, int cardNumber, double value) {
        this(0, 0, establishmentName, productDescription, dateBuy, cardNumber, value);

    }
}
