package br.com.alelo.consumer.consumerpat.domain.entity;

import br.com.alelo.consumer.consumerpat.domain.enumeration.CardType;
import br.com.alelo.consumer.consumerpat.domain.util.BigDecimalCalculator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 * Consumidor que possui os dados controlados e transações gerenciadas.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Consumer {

    // OBSERVAÇÃO: Não apliquei Bean Validation pois não havia nenhuma validação explicitamente definida no teste.

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;

    // CONSUMER PERSONAL DATA
    private String name;
    private Long documentNumber;
    private Date birthDate;

    // CONSUMER CONTACTS
    private Long mobilePhoneNumber;
    private Long residencePhoneNumber;
    private Long phoneNumber;
    private String email;

    // CONSUMER ADDRESS
    private String street;
    private Integer number;
    private String city;
    private String country;
    private Integer portalCode;

    // CONSUMER CARDS
    @Column(unique = true)
    private BigInteger foodCardNumber;

    //    @Column(updatable = false)
    private BigDecimal foodCardBalance;

    @Column(unique = true)
    private BigInteger fuelCardNumber;

    private BigDecimal fuelCardBalance;

    @Column(unique = true)
    private BigInteger drugstoreCardNumber;

    private BigDecimal drugstoreCardBalance;

    public BigDecimal getBalance(CardType cardType) {

        BigDecimal balance = null;

        if (CardType.DRUGSTORE_CARD.equals(cardType)) {
            balance = this.getDrugstoreCardBalance();
        }

        if (CardType.FOOD_CARD.equals(cardType)) {
            balance = this.getFoodCardBalance();
        }

        if (CardType.FUEL_CARD.equals(cardType)) {
            balance = this.getFuelCardBalance();
        }

        return balance;
    }

    public void setBalance(BigDecimal value, CardType cardType) {

        if (CardType.DRUGSTORE_CARD.equals(cardType)) {
            this.setDrugstoreCardBalance(BigDecimalCalculator.of(this.getDrugstoreCardBalance())
                    .add(value)
                    .getResult());
        }

        if (CardType.FOOD_CARD.equals(cardType)) {
            this.setFoodCardBalance(BigDecimalCalculator.of(this.getFoodCardBalance())
                    .add(value)
                    .getResult());
        }

        if (CardType.FUEL_CARD.equals(cardType)) {
            this.setFuelCardBalance(BigDecimalCalculator.of(this.getFuelCardBalance())
                    .add(value)
                    .getResult());
        }
    }

    public boolean hasCardBalanceChanged(Consumer consumer) {
        return this.hasDrugstoreCardBalanceChanged(consumer)
                || this.hasFoodCardBalanceChanged(consumer)
                || this.hasFuelCardBalanceChanged(consumer);
    }

    public boolean hasNameChanged(Consumer consumer) {
        return consumer.getName() != null && !consumer.getName().equals(name);
    }

    public boolean hasDocumentNumberChanged(Consumer consumer) {
        return consumer.getDocumentNumber() != null
                && !consumer.getDocumentNumber().equals(documentNumber);
    }

    public boolean hasBirthDateChanged(Consumer consumer) {
        return consumer.getBirthDate() != null
                && !consumer.getBirthDate().equals(birthDate);
    }

    public boolean hasMobilePhoneNumberChanged(Consumer consumer) {
        return consumer.getMobilePhoneNumber() != null
                && !consumer.getMobilePhoneNumber().equals(mobilePhoneNumber);
    }

    public boolean hasResidencePhoneNUmberChanged(Consumer consumer) {
        return consumer.getResidencePhoneNumber() != null
                && !consumer.getResidencePhoneNumber().equals(residencePhoneNumber);
    }

    public boolean hasPhoneNumberChanged(Consumer consumer) {
        return consumer.getPhoneNumber() != null
                && !consumer.getPhoneNumber().equals(phoneNumber);
    }

    public boolean hasEmailChanged(Consumer consumer) {
        return consumer.getEmail() != null
                && !consumer.getEmail().equals(email);
    }

    public boolean hasStreethanged(Consumer consumer) {
        return consumer.getStreet() != null
                && !consumer.getStreet().equals(street);
    }

    public boolean hasNumberChanged(Consumer consumer) {
        return consumer.getNumber() != null
                && !consumer.getNumber().equals(number);
    }

    public boolean hasCityChanged(Consumer consumer) {
        return consumer.getCity() != null
                && !consumer.getCity().equals(city);
    }

    public boolean hasCountryChanged(Consumer consumer) {
        return consumer.getCountry() != null
                && !consumer.getCountry().equals(country);
    }

    public boolean hasPortalCodeChanged(Consumer consumer) {
        return consumer.getPortalCode() != null
                && !consumer.getPortalCode().equals(portalCode);
    }

    public boolean hasFoodCardNumberChanged(Consumer consumer) {
        return consumer.getFoodCardNumber() != null
                && !consumer.getFoodCardNumber().equals(foodCardNumber);
    }

    public boolean hasFoodCardBalanceChanged(Consumer consumer) {
        return consumer.getFoodCardBalance() != null
                && consumer.getFoodCardBalance().compareTo(foodCardBalance) != 0;
    }

    public boolean hasFuelCardNumberChanged(Consumer consumer) {
        return consumer.getFuelCardNumber() != null
                && !consumer.getFuelCardNumber().equals(fuelCardNumber);
    }

    public boolean hasFuelCardBalanceChanged(Consumer consumer) {
        return consumer.getFuelCardBalance() != null
                && consumer.getFuelCardBalance().compareTo(fuelCardBalance) != 0;
    }

    public boolean hasDrugstoreCardNumberChanged(Consumer consumer) {
        return consumer.getDrugstoreCardNumber() != null
                && !consumer.getDrugstoreCardNumber().equals(drugstoreCardNumber);
    }

    public boolean hasDrugstoreCardBalanceChanged(Consumer consumer) {
        return consumer.getDrugstoreCardBalance() != null
                && consumer.getDrugstoreCardBalance().compareTo(drugstoreCardBalance) != 0;
    }

    public void selfUpdate(Consumer consumer) {

        if (this.hasNameChanged(consumer)) {
            this.name = consumer.getName();
        }

        if (this.hasDocumentNumberChanged(consumer)) {
            this.documentNumber = consumer.getDocumentNumber();
        }

        if (this.hasBirthDateChanged(consumer)) {
            this.birthDate = consumer.getBirthDate();
        }

        if (this.hasMobilePhoneNumberChanged(consumer)) {
            this.mobilePhoneNumber = consumer.getMobilePhoneNumber();
        }

        if (this.hasResidencePhoneNUmberChanged(consumer)) {
            this.residencePhoneNumber = consumer.getResidencePhoneNumber();
        }

        if (this.hasPhoneNumberChanged(consumer)) {
            this.phoneNumber = consumer.getPhoneNumber();
        }

        if (this.hasEmailChanged(consumer)) {
            this.email = consumer.getEmail();
        }

        if (this.hasStreethanged(consumer)) {
            this.street = consumer.getStreet();
        }

        if (this.hasNumberChanged(consumer)) {
            this.number = consumer.getNumber();
        }

        if (this.hasCityChanged(consumer)) {
            this.city = consumer.getCity();
        }

        if (this.hasCountryChanged(consumer)) {
            this.country = consumer.getCountry();
        }

        if (this.hasPortalCodeChanged(consumer)) {
            this.portalCode = consumer.getPortalCode();
        }

        if (this.hasFoodCardNumberChanged(consumer)) {
            this.foodCardNumber = consumer.getFoodCardNumber();
        }

        if (this.hasFuelCardNumberChanged(consumer)) {
            this.fuelCardNumber = consumer.getFuelCardNumber();
        }

        if (this.hasDrugstoreCardNumberChanged(consumer)) {
            this.drugstoreCardNumber = consumer.getDrugstoreCardNumber();
        }
    }

}
