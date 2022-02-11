package br.com.alelo.consumer.consumerpat.dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import lombok.Data;

@Data
public class ConsumerDTO {

    private Long id;
    
    private String name;
    private String documentNumber;
    private Date birthDate;

    //contacts
    private String mobilePhoneNumber;
    private String residencePhoneNumber;
    private String phoneNumber;
    private String email;

    //Address
    private String address1;
    private String address2;
    private String city;
    private String country;
    private String postalCode;

    
    private List<CardDTO> cards;

    public ConsumerDTO() {

    }
    
    public ConsumerDTO(Consumer entity) {
        this.birthDate = entity.getBirthDate();
        this.city = entity.getCity();
        this.country = entity.getCountry();
        this.documentNumber = entity.getDocumentNumber();
        this.email = entity.getEmail();
        this.id = entity.getId();
        this.mobilePhoneNumber = entity.getMobilePhoneNumber();
        this.name = entity.getName();
        this.address1 = entity.getAddress1();
        this.phoneNumber = entity.getPhoneNumber();
        this.postalCode = entity.getPostalCode();
        this.residencePhoneNumber = entity.getResidencePhoneNumber();
        this.address2 = entity.getAddress2();
        if (entity.getCards() != null) {
            this.cards = entity.getCards().stream().map(
                card -> new CardDTO(card)).collect(Collectors.toList()
            );
        }    
    }

}
