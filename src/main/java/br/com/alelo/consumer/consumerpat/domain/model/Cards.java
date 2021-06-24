package br.com.alelo.consumer.consumerpat.domain.model;

import br.com.alelo.consumer.consumerpat.enums.EstablishmentType;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Cards {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private EstablishmentType cardType;
    private int cardNumber;
    private double cardBalance;

    public void addBalance(double balance){
        this.cardBalance+=balance;
    }
    public void removeBalance(double balance){
        this.cardBalance-=balance;
    }

}
