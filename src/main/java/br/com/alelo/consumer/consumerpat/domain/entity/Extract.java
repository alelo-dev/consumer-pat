package br.com.alelo.consumer.consumerpat.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.annotation.CreatedDate;

import lombok.Data;


@Data
@Entity
public class Extract {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    private String establishmentName;
    private String productDescription;
    private String cardNumber;
    private BigDecimal value;

    @CreatedDate
    private LocalDateTime buyDate;

    public Extract(String establishmentName, String productDescription, String cardNumber, BigDecimal value) {
        this.establishmentName = establishmentName;
        this.productDescription = productDescription;
        this.cardNumber = cardNumber;
        this.value = value;
    }
}
