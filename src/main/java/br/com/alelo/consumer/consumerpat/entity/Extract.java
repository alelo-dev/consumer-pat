package br.com.alelo.consumer.consumerpat.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;


@Data
@Entity
@NoArgsConstructor
public class Extract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer establishmentNameId;
    private String establishmentName;
    private String productDescription;
    private LocalDateTime dateBuy;
    private String cardNumber;
    private BigDecimal value;


    public Extract(int id, int establishmentNameId, String establishmentName, String productDescription, LocalDateTime dateBuy, String cardNumber, BigDecimal value) {
        this.id = id;
        this.establishmentNameId = establishmentNameId;
        this.establishmentName = establishmentName;
        this.productDescription = productDescription;
        this.dateBuy = dateBuy;
        this.cardNumber = cardNumber;
        this.value = value;
    }

    public Extract(String productDescription, LocalDateTime dateBuy, String cardNumber, BigDecimal value) {
        this.productDescription = productDescription;
        this.dateBuy = dateBuy;
        this.cardNumber = cardNumber;
        this.value = value;
    }

    public Extract(int establishmentNameId, String establishmentName, String productDescription, LocalDateTime dateBuy, String cardNumber, BigDecimal value) {
        this.establishmentNameId = establishmentNameId;
        this.establishmentName = establishmentName;
        this.productDescription = productDescription;
        this.dateBuy = dateBuy;
        this.cardNumber = cardNumber;
        this.value = value;
    }
}
