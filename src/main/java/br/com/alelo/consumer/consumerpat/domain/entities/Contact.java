package br.com.alelo.consumer.consumerpat.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Contact {
    @JsonProperty("mobile_phone_umber")
    private String mobilePhoneNumber;
    @JsonProperty("residence_phone_number")
    private String residencePhoneNumber;
    @JsonProperty("work_phone_number")
    private String workPhoneNumber;
    private String email;

}
