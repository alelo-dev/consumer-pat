package br.com.alelo.consumer.pat.payload;

import br.com.alelo.consumer.pat.entity.Consumer;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ConsumerPayload {

    private Long consumerId;
    private String name;
    private String documentNumber;
    private LocalDate birthDate;

    //contacts
    private String mobilePhoneNumber;
    private String residencePhoneNumber;
    private String phoneNumber;
    private String email;

    //Address
    private String street;
    private Integer number;
    private String city;
    private String country;
    private Integer portalCode;

    public static ConsumerPayload from(Consumer consumer) {
        return ConsumerPayload.builder()
            .consumerId(consumer.getId())
            .name(consumer.getName())
            .documentNumber(consumer.getDocumentNumber())
            .birthDate(consumer.getBirthDate())
            .mobilePhoneNumber(consumer.getMobilePhoneNumber())
            .residencePhoneNumber(consumer.getResidencePhoneNumber())
            .phoneNumber(consumer.getPhoneNumber())
            .email(consumer.getEmail())
            .street(consumer.getStreet())
            .number(consumer.getNumber())
            .city(consumer.getCity())
            .country(consumer.getCountry())
            .portalCode(consumer.getPortalCode())
            .build();
    }

}
