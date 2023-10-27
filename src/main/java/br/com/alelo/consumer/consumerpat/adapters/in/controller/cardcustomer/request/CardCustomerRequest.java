package br.com.alelo.consumer.consumerpat.adapters.in.controller.cardcustomer.request;

import br.com.alelo.consumer.consumerpat.adapters.in.controller.customer.request.CustomerRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class CardCustomerRequest {

    private CardTypeEnum cardType;
    @NotBlank(message = "card number is required")
    private String cardNumber;

    @JsonIgnore
    private CustomerRequest customer;
}
