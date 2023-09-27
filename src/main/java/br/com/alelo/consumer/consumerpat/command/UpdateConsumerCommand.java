package br.com.alelo.consumer.consumerpat.command;

import java.util.Date;

import lombok.Data;


@Data
public class UpdateConsumerCommand implements Command{

    private String id;
    private String name;
    private int documentNumber;
    private Date birthDate;
    private int mobilePhoneNumber;
    private int residencePhoneNumber;
    private int phoneNumber;
    private String email;
    private String street;
    private int number;
    private String city;
    private String country;
    private int portalCode;
    private int foodCardNumber;
    private int fuelCardNumber;
    private int drugstoreNumber;

    public UpdateConsumerCommand() { }

    public UpdateConsumerCommand(String id, String name, int documentNumber, Date birthDate, int mobilePhoneNumber,
            int residencePhoneNumber, int phoneNumber, String email, String street, int number, String city,
            String country, int portalCode, int foodCardNumber, double foodCardBalance, int fuelCardNumber, int drugstoreNumber) {
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
                this.fuelCardNumber = fuelCardNumber;
                this.drugstoreNumber = drugstoreNumber;
        }
        
    }

