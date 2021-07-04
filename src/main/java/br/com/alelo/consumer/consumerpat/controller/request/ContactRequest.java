package br.com.alelo.consumer.consumerpat.controller.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
public class ContactRequest {

    @JsonProperty("phone_number")
    private final String phoneNumber;

    @JsonProperty("email")
    private final String email;
}
