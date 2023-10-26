package br.com.alelo.consumer.consumerpat.application.core.domain.customer;

public class Address {

    private String street;
    private String number;
    private String city;
    private String country;
    private String postalCode;

    public Address() {
    }

    public Address(String street, String number, String city, String country, String postalCode) {
        this.street = street;
        this.number = number;
        this.city = city;
        this.country = country;
        this.postalCode = postalCode;
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

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
