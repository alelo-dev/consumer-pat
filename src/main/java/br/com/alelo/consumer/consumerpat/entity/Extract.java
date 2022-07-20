package br.com.alelo.consumer.consumerpat.entity;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@Entity
@Builder
@RequiredArgsConstructor
public class Extract {

    @Id
    int id;
    int establishmentNameId;
    String establishmentName;
    String productDescription;
    LocalDateTime dateBuy;
    int cardNumber;
    double value;

}
