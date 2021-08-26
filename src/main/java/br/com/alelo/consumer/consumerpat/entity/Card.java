package br.com.alelo.consumer.consumerpat.entity;

import br.com.alelo.consumer.consumerpat.dto.EstablishmentTypeParse;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Card {

    @Id
    private Integer number;
    private CardType type;
    private BigDecimal balance;

    @ManyToOne
    private Consumer consumer;

    @OneToMany(mappedBy = "cardNumber", cascade = CascadeType.ALL)
    private List<CardHistory> cardHistory = new ArrayList();

    public Card(int number, CardType type) {
        this.number = number;
        this.type = type;
        balance = new BigDecimal(0);
    }

    protected Card() {
    }

    public void addBalance(double value) throws Exception {
        addBalance(new BigDecimal(value));
    }
    public void addBalance(BigDecimal value) throws Exception {
        if(value.doubleValue() < 0)
            throw new Exception("Não é permitido adicionar saldo negativo a conta");
        cardHistory.add(new CardHistory("Adição saldo","Adição saldo descrição",this,value));
        balance = balance.add(value);
    }

    public void buy(String establishmentName, String productDescription, BigDecimal value, int establishmentType) throws Exception {
        if(value.doubleValue() > 0)
            throw new Exception("Não é possivel realizar compras valor da transação positivo");
        if(!EstablishmentTypeParse.parse(establishmentType).equals(this.getType()))
            throw new Exception("Não é possivel realizar compras nesse estabelecimento com esse tipo de cartão");
        if(this.balance.add(value).doubleValue() < 0)
            throw new Exception("Saldo insuficiente");


        balance = balance.add(value);
        cardHistory.add(new CardHistory("Compra "+establishmentName,productDescription,this,value));
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public CardType getType() {
        return type;
    }

    public void setType(CardType type) {
        this.type = type;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Consumer getConsumer() {
        return consumer;
    }

    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }

    public List<CardHistory> getCardHistory() {
        return cardHistory;
    }

    public void setCardHistory(List<CardHistory> cardHistory) {
        this.cardHistory = cardHistory;
    }


}
