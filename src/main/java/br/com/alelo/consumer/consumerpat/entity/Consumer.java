package br.com.alelo.consumer.consumerpat.entity;


import br.com.alelo.consumer.consumerpat.exceptions.BusinessException;
import br.com.alelo.consumer.consumerpat.models.Compra;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
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

  //cards
  @Column(name = "food_card_number")
  private int foodCardNumber;

  @Column(name = "food_card_balance")
  private BigDecimal foodCardBalance;

  @Column(name = "fuel_card_number")
  private int fuelCardNumber;

  @Column(name = "fuel_card_balance")
  private BigDecimal fuelCardBalance;

  @Column(name = "drugstore_number")
  private int drugstoreNumber;

  @Column(name = "drugstore_card_balance")
  private BigDecimal drugstoreCardBalance;

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

    this.foodCardNumber = consumer.getFoodCardNumber();

    this.fuelCardNumber = consumer.getFuelCardNumber();

    this.drugstoreNumber = consumer.getDrugstoreNumber();

    return this;
  }

  public Consumer creditar(int cardNumber, BigDecimal valor) {
    if (isDrugstoreCard(cardNumber)) {
      this.drugstoreCardBalance = this.drugstoreCardBalance.add(valor);
    }

    if (isFoodCard(cardNumber)) {
      this.foodCardBalance = this.foodCardBalance.add(valor);
    }

    if (isFuelCard(cardNumber)) {
      this.fuelCardBalance = this.fuelCardBalance.add(valor);
    }

    return this;
  }

  public Consumer debitar(int cardNumber, Compra compra, BigDecimal valorCalculado) {

    if (isDrugstoreCard(cardNumber) && compra.isTypeDrugstore()) {
      assertConsumer(hasSaldo(valorCalculado, this.drugstoreCardBalance),
          "Saldo insuficiente no cartão 'Drugstore'");
      this.drugstoreCardBalance = this.drugstoreCardBalance.subtract(valorCalculado);
    }

    if (isFoodCard(cardNumber) && compra.isTypeFood()) {
      assertConsumer(hasSaldo(valorCalculado, this.foodCardBalance),
          "Saldo insuficiente no cartão 'Food'");
      this.foodCardBalance = this.foodCardBalance.subtract(valorCalculado);
    }

    if (isFuelCard(cardNumber) && compra.isTypeFuel()) {
      assertConsumer(hasSaldo(valorCalculado, this.fuelCardBalance),
          "Saldo insuficiente no cartão 'Fuel'");
      this.fuelCardBalance = this.fuelCardBalance.subtract(valorCalculado);
    }

    return this;
  }

  private boolean hasSaldo(BigDecimal valor, BigDecimal p) {
    return p.compareTo(valor) < 0;
  }

  private boolean isDrugstoreCard(int cardNumber) {
    return Objects.equals(this.drugstoreNumber, cardNumber);
  }

  private boolean isFoodCard(int cardNumber) {
    return Objects.equals(this.foodCardNumber, cardNumber);
  }

  private boolean isFuelCard(int cardNumber) {
    return Objects.equals(this.fuelCardNumber, cardNumber);
  }

  private void assertConsumer(Boolean is, String msg) {
    if (is) {
      throw new BusinessException(msg);
    }
  }

}
