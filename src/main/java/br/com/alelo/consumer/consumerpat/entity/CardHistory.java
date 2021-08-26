package br.com.alelo.consumer.consumerpat.entity;



import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class CardHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    String name;
    String description;
    Date date;
    BigDecimal value;
    @ManyToOne
    Card cardNumber;

    public CardHistory(String name, String description, Card cardNumber, BigDecimal value) {
        this.name = name;
        this.description = description;
        this.date = new Date();
        this.cardNumber = cardNumber;
        this.value = value;
    }

    protected CardHistory() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Card getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Card cardNumber) {
        this.cardNumber = cardNumber;
    }
}
