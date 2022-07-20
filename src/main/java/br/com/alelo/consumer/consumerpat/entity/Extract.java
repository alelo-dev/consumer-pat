package br.com.alelo.consumer.consumerpat.entity;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Extract {

    @Id
    private final int id = 0;
    private int establishmentNameId;
    private String establishmentName;
    private String productDescription;
    private LocalDateTime dateBuy;
    private int cardNumber;
    private double value;

}
