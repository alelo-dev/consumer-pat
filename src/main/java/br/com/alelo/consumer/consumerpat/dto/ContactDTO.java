package br.com.alelo.consumer.consumerpat.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContactDTO {
    private Integer id;

    private Integer mobilePhoneNumber;

    private Integer residencePhoneNumber;

    private Integer phoneNumber;

    private String email;
}
