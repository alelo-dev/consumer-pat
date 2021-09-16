package br.com.alelo.consumer.consumerpat.entity;

import java.math.BigDecimal;
import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Card {
    
    @Getter
    @AllArgsConstructor
    public enum TYPE_CARD { FOOD(10,0), FUEL(0,0), DRUGS(0,35);

       private Integer discountPercent;
       private Integer additionPercent;
    }; 

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Column(unique = true)
    private String cardNumber;

    @NotNull
    private BigDecimal balance;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    private TYPE_CARD typeCard;


}