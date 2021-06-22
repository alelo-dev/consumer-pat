package br.com.alelo.consumer.consumerpat.entity;

import br.com.alelo.consumer.consumerpat.entity.enumeration.PurchaseType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String number;
    private BigDecimal balance;
    private PurchaseType type;

    @ManyToOne
    @JsonIgnore
    private Consumer consumer;

    public void registerPurchase(BigDecimal productValue) {
        balance = balance.subtract(productValue);
    }
}
