package br.com.alelo.consumer.consumerpat.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(NON_EMPTY)
public class ClientDTO {
    private Integer id;
    private String name;
    private String documentNumber;
    private Date birthDate;

    private String street;
    private int number;
    private String city;
    private String country;
    private int portalCode;

    private String mobilePhoneNumber;
    private String residencePhoneNumber;
    private String phoneNumber;
    private String email;
}
