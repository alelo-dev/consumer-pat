package br.com.alelo.consumer.consumerpat.application.core.domain.customer;

public class Contact {

    private String mobilePhoneNumber;
    private String residencePhoneNumber;
    private String email;

    public Contact() {
    }

    public Contact(String mobilePhoneNumber, String residencePhoneNumber, String email) {
        this.mobilePhoneNumber = mobilePhoneNumber;
        this.residencePhoneNumber = residencePhoneNumber;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
