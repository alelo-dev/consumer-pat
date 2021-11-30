package br.com.alelo.consumer.consumerpat.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

}
