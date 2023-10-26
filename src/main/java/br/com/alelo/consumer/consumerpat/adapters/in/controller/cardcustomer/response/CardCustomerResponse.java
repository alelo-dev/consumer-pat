package br.com.alelo.consumer.consumerpat.adapters.in.controller.cardcustomer.response;

import br.com.alelo.consumer.consumerpat.adapters.in.controller.customer.response.CustomerResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardCustomerResponse {
    private UUID id;
    private String cardType;
    private String cardNumber;
    private BigDecimal cardBalance;

    private CustomerResponse customer;
}
