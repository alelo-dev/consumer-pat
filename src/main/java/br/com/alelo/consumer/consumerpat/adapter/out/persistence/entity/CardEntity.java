package br.com.alelo.consumer.consumerpat.adapter.out.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "card")
public class CardEntity {

  @Id
  private Integer number;
  private Double balance;
  private String type;

  @ManyToOne(fetch = FetchType.LAZY)
  private ConsumerEntity consumer;

  public Integer getNumber() {
    return number;
  }

  public void setNumber(Integer number) {
    this.number = number;
  }

  public Double getBalance() {
    return balance;
  }

  public void setBalance(Double balance) {
    this.balance = balance;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public ConsumerEntity getConsumer() {
    return consumer;
  }

  public void setConsumer(ConsumerEntity consumer) {
    this.consumer = consumer;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof CardEntity))
      return false;
    return number != null && number.equals(((CardEntity) o).getNumber());
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }

}
