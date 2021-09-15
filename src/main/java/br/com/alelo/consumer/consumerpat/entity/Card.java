package br.com.alelo.consumer.consumerpat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Card implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private Consumer consumer;

    @Column(name = "TYPE")
    private BusinessType type;

    @Column(name = "NUMBER")
    private int number;

    @Column(name = "BALANCE")
    private double balance;


    public void debit(double value){
        setBalance(this.balance - value);
    }

    public void credit(double value){
        setBalance(this.balance + value);
    }

}
