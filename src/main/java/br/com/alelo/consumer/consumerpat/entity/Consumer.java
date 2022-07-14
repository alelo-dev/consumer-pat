package br.com.alelo.consumer.consumerpat.entity;


import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "consumer")
public class Consumer {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column(name = "name")
  private String name;

  @Column(name = "document_number")
  private int documentNumber;

  @Column(name = "birth_date")
  private LocalDate birthDate;

  //contacts
  @Column(name = "mobile_phone_number")
  private int mobilePhoneNumber;

  @Column(name = "residence_phone_number")
  private int residencePhoneNumber;

  @Column(name = "phone_number")
  private int phoneNumber;

  @Column(name = "email")
  private String email;

  //Address
  @Column(name = "street")
  private String street;

  @Column(name = "number")
  private int number;

  @Column(name = "city")
  private String city;

  @Column(name = "country")
  private String country;

  @Column(name = "portal_code")
  private int portalCode;

  public Consumer update(Consumer consumer) {

    this.name = consumer.getName();

    this.documentNumber = consumer.getDocumentNumber();

    this.birthDate = consumer.getBirthDate();

    this.mobilePhoneNumber = consumer.getMobilePhoneNumber();

    this.residencePhoneNumber = consumer.getResidencePhoneNumber();

    this.phoneNumber = consumer.getPhoneNumber();

    this.email = consumer.getEmail();

    this.street = consumer.getStreet();

    this.number = consumer.getNumber();

    this.city = consumer.getCity();

    this.country = consumer.getCountry();

    this.portalCode = consumer.getPortalCode();

    return this;
  }


}
