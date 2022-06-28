package br.com.alelo.consumer.consumerpat.entity;


import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


@Data
@Entity
public class Consumer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Integer id;
    String name;
    Integer documentNumber;
    Date birthDate;

    //contacts
    private String mobilePhoneNumber;
    private String residencePhoneNumber;
    private String phoneNumber;
    private String email;

    //Address
    private String street;
    private String number;
    private String city;
    private String country;
    private String portalCode;

    //cards
    private Long  foodCardNumber;
    private Double foodCardBalance;

    private Long  fuelCardNumber;
    private Double fuelCardBalance;

    private Integer drugstoreNumber;
    private Double drugstoreCardBalance;

    public Consumer() {
    }

    public Consumer(Integer id, String name, Integer documentNumber, Date birthDate, String mobilePhoneNumber,
                    String residencePhoneNumber, String phoneNumber, String email, String street, String number,
                    String city, String country, String portalCode, Long  foodCardNumber, Double foodCardBalance,
                    Long  fuelCardNumber, Double fuelCardBalance, Integer drugstoreNumber,
                    Double drugstoreCardBalance) {
        this.id = id;
        this.name = name;
        this.documentNumber = documentNumber;
        this.birthDate = birthDate;
        this.mobilePhoneNumber = mobilePhoneNumber;
        this.residencePhoneNumber = residencePhoneNumber;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.street = street;
        this.number = number;
        this.city = city;
        this.country = country;
        this.portalCode = portalCode;
        this.foodCardNumber = foodCardNumber;
        this.foodCardBalance = foodCardBalance;
        this.fuelCardNumber = fuelCardNumber;
        this.fuelCardBalance = fuelCardBalance;
        this.drugstoreNumber = drugstoreNumber;
        this.drugstoreCardBalance = drugstoreCardBalance;
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

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    public String getResidencePhoneNumber() {
        return residencePhoneNumber;
    }

    public void setResidencePhoneNumber(String residencePhoneNumber) {
        this.residencePhoneNumber = residencePhoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
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

    public String getPortalCode() {
        return portalCode;
    }

    public void setPortalCode(String portalCode) {
        this.portalCode = portalCode;
    }

    public Long getFoodCardNumber() {
        return foodCardNumber;
    }

    public void setFoodCardNumber(Long  foodCardNumber) {
        this.foodCardNumber = foodCardNumber;
    }

    public Double getFoodCardBalance() {
        return foodCardBalance;
    }

    public void setFoodCardBalance(Double foodCardBalance) {
        this.foodCardBalance = foodCardBalance;
    }

    public Long getFuelCardNumber() {
        return fuelCardNumber;
    }

    public void setFuelCardNumber(Long  fuelCardNumber) {
        this.fuelCardNumber = fuelCardNumber;
    }

    public Double getFuelCardBalance() {
        return fuelCardBalance;
    }

    public void setFuelCardBalance(Double fuelCardBalance) {
        this.fuelCardBalance = fuelCardBalance;
    }

    public Integer getDrugstoreNumber() {
        return drugstoreNumber;
    }

    public void setDrugstoreNumber(Integer drugstoreNumber) {
        this.drugstoreNumber = drugstoreNumber;
    }

    public Double getDrugstoreCardBalance() {
        return drugstoreCardBalance;
    }

    public void setDrugstoreCardBalance(Double drugstoreCardBalance) {
        this.drugstoreCardBalance = drugstoreCardBalance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Consumer)) return false;
        Consumer consumer = (Consumer) o;
        return id.equals(consumer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
