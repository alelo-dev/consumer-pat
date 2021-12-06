package br.com.alelo.consumer.consumerpat.adapter.out.persistence.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ExtractEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  private String establishmentName;
  private String productDescription;
  private Date dateBuy;
  private Integer cardNumber;
  private Double value;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getEstablishmentName() {
    return establishmentName;
  }

  public void setEstablishmentName(String establishmentName) {
    this.establishmentName = establishmentName;
  }

  public String getProductDescription() {
    return productDescription;
  }

  public void setProductDescription(String productDescription) {
    this.productDescription = productDescription;
  }

  public Date getDateBuy() {
    return dateBuy;
  }

  public void setDateBuy(Date dateBuy) {
    this.dateBuy = dateBuy;
  }

  public Integer getCardNumber() {
    return cardNumber;
  }

  public void setCardNumber(Integer cardNumber) {
    this.cardNumber = cardNumber;
  }

  public Double getValue() {
    return value;
  }

  public void setValue(Double value) {
    this.value = value;
  }
}
