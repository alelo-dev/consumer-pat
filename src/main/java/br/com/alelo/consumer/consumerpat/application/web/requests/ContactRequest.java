package br.com.alelo.consumer.consumerpat.application.web.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ContactRequest {
    @JsonProperty("mobile_phone_number")
    private String mobilePhoneNumber;
    @JsonProperty("residence_phone_number")
    private String residencePhoneNumber;
    @JsonProperty("work_phone_number")
    private String workPhoneNumber;
    private String email;

}
