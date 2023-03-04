package br.com.alelo.consumer.consumerpat.domain.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class Extract {

    @JsonProperty("establishment_name")
    private String establishmentName;
    @JsonProperty("product_description")
    private String productDescription;
    @JsonProperty("date_buy")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dateBuy;
    @JsonProperty("card_number")
    private String cardNumber;
    private double amount;
    @JsonIgnore
    private int consumerId;

    public Extract(
            String establishmentName,
            String productDescription,
            LocalDate dateBuy,
            String cardNumber,
            double amount,
            int consumerId
    ) {
        this.establishmentName = establishmentName;
        this.productDescription = productDescription;
        this.dateBuy = dateBuy;
        this.cardNumber = cardNumber;
        this.amount = amount;
        this.consumerId = consumerId;
    }
}
