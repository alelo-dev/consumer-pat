package br.com.alelo.consumer.consumerpat.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


@Data
@Entity
public class Extract {

    @Id
    private Integer id;
    private Integer establishmentNameId;
    private String establishmentName;
    private String productDescription;
    private LocalDateTime dateBuy;
    private Integer cardNumber;
    private BigDecimal value = BigDecimal.ZERO;

    public Extract(Integer id, Integer establishmentNameId, String establishmentName, String productDescription,
                   LocalDateTime dateBuy, Integer cardNumber, BigDecimal value) {
        this.id = id;
        this.establishmentNameId = establishmentNameId;
        this.establishmentName = establishmentName;
        this.productDescription = productDescription;
        this.dateBuy = dateBuy;
        this.cardNumber = cardNumber;
        this.value = value;
    }

    public Extract(String productDescription, LocalDateTime dateBuy, Integer cardNumber, BigDecimal value) {
        this.productDescription = productDescription;
        this.dateBuy = dateBuy;
        this.cardNumber = cardNumber;
        this.value = value;
    }

    public Extract(String establishmentName, String productDescription, LocalDateTime dateBuy,
                   Integer cardNumber, BigDecimal value) {
        this.establishmentNameId = establishmentNameId;
        this.establishmentName = establishmentName;
        this.productDescription = productDescription;
        this.dateBuy = dateBuy;
        this.cardNumber = cardNumber;
        this.value = value;
    }
}
