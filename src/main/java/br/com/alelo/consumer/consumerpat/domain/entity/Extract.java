package br.com.alelo.consumer.consumerpat.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Extract extends EntidadeBase<Integer>{

    @Id
    Integer id;
    Integer establishmentNameId;
    String establishmentName;
    String productDescription;

    @Column(columnDefinition = "TIMESTAMP")
    LocalDateTime dateBuy;

    Integer cardNumber;
    BigDecimal value;

    public Extract(String productDescription, LocalDateTime dateBuy, Integer cardNumber, BigDecimal value) {
        this.productDescription = productDescription;
        this.dateBuy = dateBuy;
        this.cardNumber = cardNumber;
        this.value = value;
    }

    public Extract(String establishmentName, String productDescription, LocalDateTime dateBuy, Integer cardNumber, BigDecimal value) {
        this.establishmentName = establishmentName;
        this.productDescription = productDescription;
        this.dateBuy = dateBuy;
        this.cardNumber = cardNumber;
        this.value = value;
    }

}
