package br.com.alelo.consumer.consumerpat.requests;

import lombok.Data;

@Data
public class BuyRequest {
    public int establishmentType;
    public String establishmentName;
    public String productDescription;
    public int cardNumber;
    public double value;
}
