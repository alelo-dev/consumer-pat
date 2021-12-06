package br.com.alelo.consumer.consumerpat.adapter.out.persistence.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "consumer")
public class ConsumerEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  private String name;
  private Integer documentNumber;
  private Date birthDate;
  private Integer mobilePhoneNumber;
  private Integer residencePhoneNumber;
  private Integer phoneNumber;
  private String email;
  private String street;
  private Integer number;
  private String city;
  private String country;
  private Integer portalCode;

  @OneToMany(mappedBy = "consumer", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<CardEntity> cards = new ArrayList<CardEntity>();

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

  public Integer getDocumentNumber() {
    return documentNumber;
  }

  public void setDocumentNumber(Integer documentNumber) {
    this.documentNumber = documentNumber;
  }

  public Date getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(Date birthDate) {
    this.birthDate = birthDate;
  }

  public Integer getMobilePhoneNumber() {
    return mobilePhoneNumber;
  }

  public void setMobilePhoneNumber(Integer mobilePhoneNumber) {
    this.mobilePhoneNumber = mobilePhoneNumber;
  }

  public Integer getResidencePhoneNumber() {
    return residencePhoneNumber;
  }

  public void setResidencePhoneNumber(Integer residencePhoneNumber) {
    this.residencePhoneNumber = residencePhoneNumber;
  }

  public Integer getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(Integer phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public Integer getNumber() {
    return number;
  }

  public void setNumber(Integer number) {
    this.number = number;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public Integer getPortalCode() {
    return portalCode;
  }

  public void setPortalCode(Integer portalCode) {
    this.portalCode = portalCode;
  }

  public void addCard(CardEntity card) {
    cards.add(card);
    card.setConsumer(this);
  }
  
  public List<CardEntity> getCards() {
    return cards;
  }

  public void setCards(List<CardEntity> cards) {
    this.cards = cards;
  }

}
