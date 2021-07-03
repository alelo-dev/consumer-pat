package br.com.alelo.consumer.consumerpat.integration.rest.controller.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContactRequestV1 {
    private Long mobilePhoneNumber;
    private Long residencePhoneNumber;
    private Long phoneNumber;
    private String email;
}
