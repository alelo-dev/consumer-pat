package br.com.alelo.consumer.consumerpat.model.request;

import br.com.alelo.consumer.consumerpat.entity.Establishment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuyRequest {

    private Establishment establishment;

    private String cardNumber;

    private String consumerDocumentNumber;

    private String productDescription;

    private Date dateBuy;

    private double value;

}
