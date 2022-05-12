package br.com.alelo.consumer.consumerpat.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddBalanceRequest {

    private String consumerDocumentNumber;

    private String cardNumber;

    private double value;
}
