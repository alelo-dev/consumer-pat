package br.com.alelo.consumer.consumerpat.entity;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsumerCardDTO {

    //cards
	private int cardNumber;
	private int establishmentId;
    private double value;
    private int establishmentType;
    private String establishmentName;
    private String productDescription;
    
}
