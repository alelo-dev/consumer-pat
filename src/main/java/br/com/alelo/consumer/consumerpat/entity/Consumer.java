package br.com.alelo.consumer.consumerpat.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;



@Entity
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    String name;
    int documentNumber;
    Date birthDate;

    int foodCardNumber;
    double foodCardBalance;

    int fuelCardNumber;
    double fuelCardBalance;

    int drugstoreNumber;
    double drugstoreCardBalance;


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

    public int getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(int documentNumber) {
        this.documentNumber = documentNumber;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public int getFoodCardNumber() {
        return foodCardNumber;
    }

    public void setFoodCardNumber(int foodCardNumber) {
        this.foodCardNumber = foodCardNumber;
    }

    public double getFoodCardBalance() {
        return foodCardBalance;
    }

    public void setFoodCardBalance(double foodCardBalance) {
        this.foodCardBalance = foodCardBalance;
    }

    public int getFuelCardNumber() {
        return fuelCardNumber;
    }

    public void setFuelCardNumber(int fuelCardNumber) {
        this.fuelCardNumber = fuelCardNumber;
    }

    public double getFuelCardBalance() {
        return fuelCardBalance;
    }

    public void setFuelCardBalance(double fuelCardBalance) {
        this.fuelCardBalance = fuelCardBalance;
    }

    public int getDrugstoreNumber() {
        return drugstoreNumber;
    }

    public void setDrugstoreNumber(int drugstoreNumber) {
        this.drugstoreNumber = drugstoreNumber;
    }

    public double getDrugstoreCardBalance() {
        return drugstoreCardBalance;
    }

    public void setDrugstoreCardBalance(double drugstoreCardBalance) {
        this.drugstoreCardBalance = drugstoreCardBalance;
    }
}
